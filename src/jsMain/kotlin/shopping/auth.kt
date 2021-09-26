package shopping

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize
import kotlinx.coroutines.launch
import shopping.model.User

//val firebaseJs = firebase.initializeApp(
//  mapOf(
//    "apiKey" to "AIzaSyDKADz-w308PlrURBCwRE8sq6XTDAG7NpU",
//    "authDomain" to "kotlin-react-shopping-2e94a.firebaseapp.com",
//    "projectId" to "kotlin-react-shopping-2e94a",
//    "storageBucket" to "kotlin-react-shopping-2e94a.appspot.com",
//    "messagingSenderId" to "393803784285",
//    "appId" to "1:393803784285:web:aed985a053aa6a005badb4",
//    "measurementId" to "G-FFJPXB75FG"
//  )
//)
val firebaseApp = Firebase.initialize(options=FirebaseOptions(

  apiKey= "AIzaSyDKADz-w308PlrURBCwRE8sq6XTDAG7NpU",
  authDomain= "kotlin-react-shopping-2e94a.firebaseapp.com",
  projectId= "kotlin-react-shopping-2e94a",
  storageBucket= "kotlin-react-shopping-2e94a.appspot.com",
  gcmSenderId= "393803784285",
  applicationId= "1:393803784285:web:aed985a053aa6a005badb4",
//  measurementId= "G-FFJPXB75FG"
))

val firebaseAuth = Firebase.auth

fun FirebaseUser.toUser() = User(uid, displayName, email, this.metaData!!.creationTime!!)

val authStateChanged = firebaseAuth.authStateChanged

val google: dynamic = shopping.firebase.firebase.auth.GoogleAuthProvider()

fun signInWithGoogle() {
  scope.launch {
    firebaseAuth.js.signInWithPopup(google)
  }
}