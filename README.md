# MusictifyApp


---Internship Project ---

 This project is a basic implementation of the app which is called Spotify.
 The app welcomes the users with the login screen. Users can easily sign up by using their email and password combinations.
 This project uses Firebase to store and verify the login information. If user types the correct combination, then the app opens.
 The homepage contains an options menu, and four bottons. Options menu allows users to create playlists or sign out. 
 The four buttons on the homepage are: liked songs, playlists, artists and albums. If users click on the liked songs button,
 then a fragment opens up. User can see the songs which I store in the firebase storage. The application receives this data 
 asynchronously from the Internet. Then, if users clicks on these songs, it starts to playing. User also can switch between the songs 
 and stops the song whenever he/she wants. If the user wants to create playlist, he/she can open the options menu and click the 
 create playlist button. It allows user to select a cover photo and give it a name. When the save button is pressed, the application 
 automatically opens the playlist page and you can see the data it saved here. The user can also view the artist and album the song belongs to.
 If user clicks the sign out button, application returns to the login page. 


 This project uses:
 MVVM, Firebase, SQLite, recyclerView, viewBinding, fragments, navigation, coroutines, picasso, glide, dagger, hilt, exoplayer.