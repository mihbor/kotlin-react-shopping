package shopping

import dev.gitlive.firebase.decode
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.RProps
import react.child
import react.functionalComponent
import react.redux.useDispatch
import react.router.dom.browserRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch
import react.useEffect
import redux.RAction
import redux.WrapperAction
import shopping.components.header
import shopping.db.createUserProfile
import shopping.model.User
import shopping.pages.checkout
import shopping.pages.homePage
import shopping.pages.shopPage
import shopping.redux.*

val app = functionalComponent<RProps> {

  val dispatch = useDispatch<RAction, WrapperAction>()
  val user = getUser()

  fun signOut() {
    dispatch(UserSignedOut())
  }

  fun signIn(user: User) {
    dispatch(UserSignedIn(user))
  }

  useEffect(emptyList()) {
    scope.launch {
      authStateChanged.collect {
        it?.let {
          createUserProfile(it.toUser())
            .onSnapshot({ snapshot ->
              decode<User?>(snapshot.data()) ?.let { signIn(it) } ?: signOut()
            }, { console.log(it)} )
        } ?: signOut()
      }
    }
    scope.launch {
      dispatch(PopulateSections(API.getSections()))
    }
// collections come from firbase now via Shop page
//    scope.launch {
//      dispatch(PopulateCollections(API.getCollections()))
//    }
  }

  browserRouter {
    child(header) { }
    switch {
      route("/", exact = true) {
        child(homePage) { }
      }
      route("/shop") {
        child(shopPage) { }
      }
      route("/checkout", exact = true) {
        child(checkout) { }
      }
      route("/login", exact = true) {
        user?.let{ redirect(to="/") } ?: child(shopping.pages.loginPage) { }
      }
    }
  }
}