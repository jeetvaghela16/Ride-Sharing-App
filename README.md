# Ride Sharing App

Company: CODTECH IT SOLUTION

Name: Vaghela Jeet Vijaybhai

Intern Id: CT04DM877

Domain: Android Development

Duration: 4 Weeks 

Mentor: Neela Santhosh Kumar

# Description 

The Ride Sharing App is a plain and functional Android prototype built with Java and Android Studio. 

The app presents fundamental elements of contemporary ride-sharing websites and apps, including location tracking, submitting ride requests, and simulated driver assignment. Developed with Google Maps SDK and Firebase Realtime Database, it is a perfect learning project for new developers who want to learn how to incorporate maps, GPS capability, and cloud database into Android apps. The app employs FusedLocationProviderClient to retrieve the current location of the user and shows it on an interactivity-enabled Google Map inserted through SupportMapFragment.

When started, the app asks for location permission and, upon granting, loads and plots the user's current location on the map. The layout is developed with a RelativeLayout that places the Google Map as the background and superimposes a ride request button and a TextView for displaying driver information. When the Request Ride button is clicked, the application takes the user's location and saves it in Firebase Realtime Database with a one-of-a-kind request ID. This illustrates the possibility of sending dynamic information (in this case, longitude and latitude) to a cloud backend in real time.

To improve the user experience, the app emulates assigning a driver once the ride request is sent. The app shows hardcoded driver information such as name, vehicle type, and contact number, which gives a realistic sense of how ride-matching systems work. This can be extended in future versions to retrieve actual driver data from a backend or connect with a driver-side app for real-time ride assignment.

The handling of location access and permissions is well taken care of with runtime permission checks, making the app secure and aligned with contemporary Android development. There are also fallbacks like attempting to get a new location when the last available one is not accessible. This solid handling makes sure the ride request feature still works when device conditions fluctuate.

This ride-sharing proof-of-concept is a good starting point for students or developers looking to build full-fledged ride-hailing apps. Major areas of expansion include incorporating Firebase Authentication, actual driver tracking, distance/time calculation, fare computation, and live status reporting. Firebase allows easy scaling and storing of user data securely, while Google Maps integration provides accurate and timely location tracking.

In total, the Ride Sharing App illustrates key Android concepts like map rendering, GPS location tracking, permission management, asynchronous Firebase calls, and responsive UI updates. It is well-structured, modular, and readable code ideal for teaching, portfolio projects, or as a starting point for more advanced transportation-related applications.

The project is open source and ready for further enhancement or integration. Fork, customize, or extend this project to create features such as driver notification alerts, payment gateways, ride records, or chat software. This is a wonderful hands-on project to learn real-time app development and how to develop user-focused mobile solutions using current tools and APIs.
