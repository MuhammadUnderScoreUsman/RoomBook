# Sample project for Room Relational Database

This Sample is just to demonstrate how relational database works in Room.<br/>
There is no other framework used in this playground. Primarily it is for focusing on Relational Databse using Room with Basic MVVM approach.<br/>
The use case is pretty simple. In One To One Relation a user can have only one account on one roomail address.<br/>
User roomail and username will be saved in ``user`` and ``user account`` table through relationship. If user try to make another account on same roomail address the dialog will appear with some important message. Once you are done with account try to signin with username and you will be procceed towards the post fragment.<br/>
Similarly there is one to many Relation as well. Where user can create post by clicking on Click Button and post with be create by signed in user.<br/>
All the above process mentioned are happening through *Room Relations*. Saving the user, querying the user, checking for the users, creating post.<br/>
**Recommendation:** *Try to use *Database Inspector*(A new feature in android studio to inspect your database). It's
gonna be helpful to see that how Relations are being managed inside database.*<br/>
This project is based on following dependencies:<br/>
> Livedata/Lifecycle<br/> Fragmentktx<br/> Coroutines<br/> Room<br/>
For dialogs: https://github.com/afollestad/material-dialogs
