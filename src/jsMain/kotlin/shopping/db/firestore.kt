package shopping.db

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.decode
import dev.gitlive.firebase.encode
import dev.gitlive.firebase.firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import shopping.firebaseAuth
import shopping.model.Collection
import shopping.model.User
import shopping.scope

val firestore = Firebase.firestore.js

suspend fun createUserProfile(user: User): firebase.firestore.DocumentReference {
  val doc = firestore.doc("users/${user.id}")
  val snapshot = doc.get().await()
  if (!snapshot.exists && user.displayName != null) {
    console.log("Snapshot for user ${user.email} doesn't exist, setting with display name ${user.displayName}")
    doc.set(encode(User(user.id, user.displayName, user.email, user.createdAt), false)!!, mapOf("merge" to true)).await()
  }
  return doc
}

fun createUserWithEmailAndPassword(email: String, password: String, displayName: String, callback: () -> Unit) {

  scope.launch {
    try {
      val user = firebaseAuth.createUserWithEmailAndPassword(email, password).user
      user?.let{
        createUserProfile(User(it.uid, displayName, it.email, it.metaData!!.creationTime!!))
        callback()
      }
    } catch (e: Throwable) {
      console.log(e)
      e.message?.let(window::alert)
    }
  }
}

suspend fun getCollections() = firestore.collection("collections").get().await().docs.map {
  decode<Collection>(it.data())
}.toList()

suspend fun storeCollections(collections: List<Collection>) {
  val ref = firestore.collection("collections")
  val batch = firestore.batch()
  collections.forEach {
    batch.set(ref.doc(it.routeName), encode(it, false)!!)
  }
  return batch.commit().await()
}