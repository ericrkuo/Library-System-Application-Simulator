# Library-System-Application-Simulator

This application is a Windows OS program that allows users to borrow/return media items from a simulated library framework.  

## Motivation
I've always loved reading books and watching movies, thus I wanted to simulate a user experience for borrowing media items as accurately as I could. This was also my first project in Java and OOP. 

## Tech/framework used
<b>Built with</b>
- JavaFX Software Platform
- images from [Google Images](https://images.google.com/)

## Features
All media items can be filtered by their availability, their types (books or movies), and their categories (action, adventure, etc.). Users can also search up media items by their title or identification numbers. The application also saves your previous session information including the number of items you borrowed, and which items were borrowed. 

## Screenshots
Users are first navigated towards the user menu where they can choose how they want to navigate through the library application.
![readMeMenu](https://user-images.githubusercontent.com/49849754/65392140-4d7c5380-dd26-11e9-9b34-a3a8d017a66a.jpg)

Below is a screenshot of all available media in the library, this can be filtered by availability, and by types such as movies or books. Users can click on the media item they want and click borrow/return on the desired media item.
![readMeViewAll](https://user-images.githubusercontent.com/49849754/65392151-58cf7f00-dd26-11e9-8be8-1ebf7d82a553.jpg)

Users can search up media items by their title or their unique identification number, and view the availability of the item. 
![readMeSearch](https://user-images.githubusercontent.com/49849754/65392167-8288a600-dd26-11e9-926f-6c97a80c7bb3.jpg)

One can view all the genres of media items such as fiction, action, romance, comedy, and many more categories to filter through from. 
![readMeSearchCategory](https://user-images.githubusercontent.com/49849754/65392148-5705bb80-dd26-11e9-8723-84f2b199f2f7.jpg)

## How to use?
Download the project and click run on the LibrarySystemUI class inside the ui package. 

## Improvements to be made
<b>Future improvements:<n>
  - [ ] adding a login ID so the application can be used with multiple account holders.
  - [ ] saving media items to an external database rather than locally.
  - [ ] add more types of media items such as CD's, videogames, textbooks, etc. 
  - [ ] use a books/movies database API to parse and load more media items.
  - [ ] edit the search functionality to be able to generate results even with typos in spelling. 

## License
MIT License

Copyright (c) [2019] [Eric Kuo]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
