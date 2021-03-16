Based on Udemy course udemy.com/course/complete-react-developer-zero-to-mastery
but using Kotlin instead of Javascript.

Firebase policy to apply:
```
service cloud.firestore {
  match /databases/{database}/documents {
	match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
	match /collections/{collection} {
      allow read: if true;
    }
  }
}
```