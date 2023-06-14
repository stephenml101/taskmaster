# taskmaster

## Lab 26 May 22, 2023

- Customized home page and all tasks page
- Created functionality to be able to navigate between the pages
- When the add task button is clicked, "Submitted" shows up
- See screenshots below:

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab26-1.png)
![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab26-2.png)
![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab26-3.png)

## Lab 27 May 23, 2023

- Remade home page and replaced photo with 3 buttons
- Added settings page functionality and when a nickname is entered, it saves on the homepage as well.
- Hard coded 3 task buttons and when they are clicked, the title shows up on the task details page.
- Added a unit test
- Added APK in main
- See screenshots bellow
- Lab time to complete: 5 hours

### Settings Page

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab27-1.png)

### New Home Page

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab27-2.png)

### 3 Pages from buttons clicked on Home Page

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab27-3.png)
![Photo 4](./src/main/java/com/stephenml101/taskmaster/screenshots/lab27-4.png)
![Photo 5](./src/main/java/com/stephenml101/taskmaster/screenshots/lab27-5.png)

## Lab 28 May 24, 2023

- Added functionality to scroll through tasks
- User can click on a task and the app will take them to the TaskDetailActivity page with the updated title
- APK updated
- Added enum class for the Task Model with new, assigned, in progress, and complete taskState (located under models)
- See main update to homepage with Recycler. I kept the original buttons in case I wanted to do something with them later.
- To do later: make margin between recycler buttons smaller and add more css.

### Updated Home Page w/ Recycler

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab28-1.png)

### Large margin example

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab28-2.png)

## Lab 29

- Added functionality so that the user can add a task, task description, and date

- When user clicks on the task on the recycler, the app takes them to the task detail page with the description

### Task with description

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab29-1.png)

### New updated Recycler

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab29-2.png)

### New Task Detail Page

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab29-3.png)


## Lab 31 May 30, 2023

- Added all tests to project
  - ActivityDisplayTest
  - AllElementsDisplayedTest
  - UsernamHomepageTest

## Lab 32 May 31, 2023

- Refactored to add all data to DynamoDB
- All tasks show up
- Description also shows up when task is clicked
- Date was added to task as well

### Task with date showing

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab32-1.png)

## Lab 33

- Completed Teams Class and populated task in database
- Still need to add to settings page

### Team View

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab33-1.png)

### Database with ID

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab33-2.png)


## Lab 34

- Added to Google Play Store. See screenshot below:

### Google Playstore Screenshot

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab34-1.png)


## Lab 36 

- Cognito added with signup page 
- Signup verification page added
- Login page added
- Display username at home page
- Logout added

### Username

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab36-1.png)

### Signup

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab36-2.png)

### Verification email

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab36-3.png)

### Verification Entered

![Photo 4](./src/main/java/com/stephenml101/taskmaster/screenshots/lab36-4.png)

### Login Page

![Photo 5](./src/main/java/com/stephenml101/taskmaster/screenshots/lab36-5.png)


### Homepage with name display

- TODO: Adjust height so all of name displays

![Photo 6](./src/main/java/com/stephenml101/taskmaster/screenshots/lab36-6.png)


## Lab 37

- Added photo functionality to Add Task Page. See screenshots below:

### New Add Task Page w/ photo icon in top right corner

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab37-1.png)


### Android photo selection w/ photo

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab37-2.png)


### S3 Storage w/ jpg name in bucket

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab37-3.png)

### New photo on Add Task Page

![Photo 4](./src/main/java/com/stephenml101/taskmaster/screenshots/lab37-4.png)


Lab 39

- Location enabled
- Was able to get it to show up on LogCat but not the taskDetailPage

### Location Permission

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab39-1.png)

### Logcat Location

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab39-2.png)

### No Location displayed. Was able to get that to show.

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab39-3.png)

## Lab 39 Update: Code fixed and location is showing on TaskDetailActivity

![Photo 4](./src/main/java/com/stephenml101/taskmaster/screenshots/lab39-4.png)


## Lab 41

- Added analytics for when the app is opened (see screenshot of AWS Pinpoint)

- Added speak button, no errors occur when button is pressed

- Translate text to spanish added to MainActivity for Second Predictions Integration

### AWS Pinpoint

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab41-1.png)

### Speak Button

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab41-2.png)

### Text Translate

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab41-3.png)


## Lab 42

- All methods added for Interstitial Ad and Rewards Ad, as well as the Banner Ad

- There was an issue with Google AdMob. I could not update my information because the page would not load. I attached a screenshot.

- I believe that is why I have an error on my ap and it will not load. Please see the error below:

### Error

![Photo 1](./src/main/java/com/stephenml101/taskmaster/screenshots/lab42-1.png)

### AdMob issue

![Photo 2](./src/main/java/com/stephenml101/taskmaster/screenshots/lab42-2.png)

### Layout of what MainActivity should look like

![Photo 3](./src/main/java/com/stephenml101/taskmaster/screenshots/lab42-3.png)
