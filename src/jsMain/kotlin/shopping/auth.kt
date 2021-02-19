package shopping

import dev.gitlive.firebase.*
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import shopping.model.User
import kotlin.js.Date

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
val firebase = Firebase.initialize(options=FirebaseOptions(

  apiKey= "AIzaSyDKADz-w308PlrURBCwRE8sq6XTDAG7NpU",
//  authDomain= "kotlin-react-shopping-2e94a.firebaseapp.com",
  projectId= "kotlin-react-shopping-2e94a",
  storageBucket= "kotlin-react-shopping-2e94a.appspot.com",
  gcmSenderId= "393803784285",
  applicationId= "1:393803784285:web:aed985a053aa6a005badb4",
//  measurementId= "G-FFJPXB75FG"
))

val firebaseAuth = Firebase.auth

val firestore = Firebase.firestore.js

fun FirebaseUser.toUser() = User(uid, displayName, email, this.metaData!!.creationTime!!)

suspend fun createUserProfile(user: User): firebase.firestore.DocumentReference {
  val doc = firestore.doc("users/${user.id}")
  val snapshot = doc.get().await()
  if (!snapshot.exists && user.displayName != null) {
    console.log("Snapshot for user ${user.email} doesn't exist, setting with display name ${user.displayName}")
    doc.set(encode(User(user.id, user.displayName, user.email, user.createdAt), false)!!, mapOf("merge" to true)).await()
  }
  return doc
}

val authStateChanged = firebaseAuth.authStateChanged

//@JsExport
//fun signInWithGoogle() {
//  scope.launch {
//    auth.js.signInWithPopup(firebase.auth.GoogleAuthProvider())
//  }
//}