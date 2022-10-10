# ITO5046-Assignment3
====================================
Mobile and  Distributed Systems Plan
====================================
- Set up github project
- Add new project
- README.md
- Navigation drawer
  - Add DB (Android room)
      1) users
         > user id
         > name
         > password
         > email
         > age
         > gender
         > fitness level - low medium high
         > successful challenges attempted
      2) challenges
         > challenge id
         > location
         > challenge name
         > chalelnge details
      3) attempts
         > attempt id
         > user id
         > challenge id
         > date attempted
         > attempt status
      5) Firebase realtime database
         > reflect similar table structure as the room
         > sync on any action, including challenge creation and attempts
- Build individual screens
    1) login and signup screens
       > ONE screen to login (username/email and password) and the Sign up option 
                 that will direct the user to the Sign up screen and if successful 
                 they can return to the login screen and login. 
       > Data validation and error messages are implemented. 
       > Two password and two form design guidelines are followed
       > login field
       > password field
       > login button
       > register button
       > on successful registration, return to login page
    2) firebase authentication for ^
    3) Home screen
       > Display a meaningful title and picture, 
       > the current date.
       > Use Android Navigation component and navigation drawer and fragments to 
                 navigate to other screens.
       > add welcome message, Welcome! Current Date: Last challenge date:
    
    4) Data entry screen - this is now the create challenge screen
       > Allow the user to enter details with appropriate UI elements. 
       > Add the Save and Edit buttons. 
       > After Save, disable data entry, enabled if Edit is clicked.
       > Provide confirmation or error messages.
    5) Data showcase and Android room - table view - this is now the view challenge screen
       > Store all data using the Android room with all the components(repository, 
                 ViewModel, LiveData) in a table.
       > The Save button in Task 3 will insert all this data in room and the update
                 button will update the table in room. 
    
    6) History and stats
       > charts - showing challenge attempts over the last 7 days
       > Statistics of past challenges
       > Show last 10 challenges and the dates attemtped
    7) Challenge search screen
       > recycler view
       > add a button to show mapbox of location
       > add a button to delete challenge
       > add a create chalelnge button
       > add an attempt challenge button
       > Mapbox integration - add button to each thing to show map of challenge area
    8) Create challenge screen
       > title field
       > details field
       > set location from location id
       > submit button to save challenge to room
    9) Attempt challenge screen
       > Displays the title and details
       > success and failure status button
       > atttempts will set the date when the page is opened, and the status when
                  the status button is clicked
    10) Profile edit screen
        > this is essentially the register screen with the fields filled in. the id
                   will be set in a hidden mode, signifying that its a profile edit
       > if the id is present, update the profile
    11) challenge leaderboard
        > pull challenges from the global rds - only the top 10 

Android User Interface - Android Design Guidelines
Online Architecture and API
Android application functionality
Exception handling & data validation
Coding standards
Project documentation and video 
Proposed features and implementation
