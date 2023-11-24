# SpringBootSecurityUserDetails
A JEE web application developed using Spring MVC, Thymeleaf, and Spring Data JPA manages patient data with features such as display, search, and deletion. This implementation optimizes data manipulation through the use of Spring MVC and Spring Data JPA frameworks. Additionally, Spring Boot Security UserDetails has been incorporated to enhance security.
## what is UserDetails
  UserDetails is an interface provided by Spring Security that represents the details of a user, including authentication and authorization information. It contains essential user information such as username, password, authorities, and other relevant details required for authentication and access control.
  Securing an app with user details involves implementing measures to protect user information from unauthorized access or misuse. This includes using strong authentication methods, encrypting sensitive data, validating and sanitizing user inputs, managing user sessions securely, and staying compliant with relevant security standards and regulations. The goal is to ensure the confidentiality, integrity, and availability of user details within the application.
## code
### HospitalAppApplication
This code establishes initial roles and users, associating roles with users, possibly for testing or seeding a database on application startup.  


![Screenshot 2023-11-23 222012](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/fed14e4d-1646-44b7-a391-2daea541648d)
### SecurityController
This controller handles two endpoints: "/notAuthorized" and "/login.  


![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/5401212f-dc1e-4eaf-a55f-71f8bc0329cf)
##### login

![Screenshot 2023-11-23 220228](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/74f61068-692a-4afa-b2a7-fc5af79d0736)

##### notAuthorized 

![Screenshot 2023-11-23 220834](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/05289c18-a9f7-4d86-9a1a-6c8189e0f8ab)
### Security package
I've introduced a new package to the project called 'entities.' Within this package, I've created two classes, namely 'AppRole' and 'AppUser.' Additionally, I've set up repositories, specifically 'AppRoleRepository' and 'AppUserRepository.' Furthermore, I've established a service layer, which includes an interface named 'AccountService' and its corresponding implementation, 'AccountServiceImplementation.' Additionally, I've implemented a class that serves as an implementation of the 'UserDetails' interface, named 'UserDetailServiceImpl and the SecurityConfig.  

![Screenshot 2023-11-23 222825](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/5c974af5-978c-488a-9ee2-80b90924ac65)

#### SecurityConfig

I've set up a JdbcUserDetailsManager bean that takes care of managing user details stored in a JDBC database. It relies on a DataSource for connecting to the database.
In addition, I've created a SecurityFilterChain bean to handle security configurations using HttpSecurity. Here's a breakdown of the setup:

For form-based authentication, I've configured httpSecurity.formLogin(). This means that users will be directed to the "/login" page, and once they successfully log in, they'll be redirected to the home page ("/").

I've allowed access to specific resources without requiring authentication. This includes resources under "/webjars/" and "/h2-console/" using httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**", "/h2-console/**").permitAll();.

To ensure that any other requests not covered by the previous rules require authentication, I've set up httpSecurity.authorizeHttpRequests().anyRequest().authenticated();.

In case access is denied, users will be redirected to the "/notAuthorized" page. This is configured with httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");

Finally, I've associated the userDetailServiceImpl with the security configuration using httpSecurity.userDetailsService(userDetailServiceImpl);. This indicates that userDetailServiceImpl is responsible for retrieving user details during the authentication process.

![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/78ef3212-67ac-4bc0-93bc-6706306bd497)

#### AppRole
the AppRole class is designed to represent a role in an application, and the annotations and Lombok features are used to reduce boilerplate code and facilitate integration with a relational database, especially when using JPA.  

![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/1b891b0c-206d-4617-b1d9-1220c7dfa841)

#### Appuser
The AppUser class is a JPA entity representing a user in a Java application, utilizing Lombok annotations for concise code generation, and it includes fields for user details such as userId, username, password, email, and a list of roles associated with the user through a Many-to-Many relationship.  
![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/38e61c44-b892-4e77-a237-903415a3bd0d)

#### AppRoleRepository 
This interface provides CRUD (Create, Read, Update, Delete) operations and other common database operations for managing AppRole entities in a Spring application.  
![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/c4c8dc83-6be7-4792-bc5c-9a65a6134505)

#### AppRoleRepository 
The AppUserRepository is a Spring Data JPA repository interface for the AppUser entity, extending JpaRepository and specifying the entity type as AppUser and the ID type as String. Additionally, it includes a custom query method, findByUsername, which allows for retrieving an AppUser by their username from the underlying database.  
![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/a4451820-3668-4079-8b2a-e59e122019aa)

#### AccountService
the AccountService provides a set of functionalities for user and role management, including user and role creation, associating roles with users, disassociating roles from users, and loading user information by username. It is designed to handle account-related operations in a modular and organized manner.  
![image](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/d2d287e6-2a76-4140-876b-7033bc4357d2)

#### AccountServiceImpl
the AccountServiceImpl class encapsulates the logic for user and role management, ensuring secure practices such as password encoding and transactional operations. The class is designed to be used within a Spring framework, particularly in a security context.  

#### AccountDetailImpl
the UserDetailServiceImpl class serves as a bridge between Spring Security's authentication mechanism and the application's user management system. It retrieves user details, including username, password, and roles, from the AccountService and constructs a UserDetails object for authentication and authorization purposes.
## Diffrence between 3 methods

InMemoryUserDetailsManager:

Implementation of UserDetailsManager in Spring Security.
Stores user details in memory.
Suitable for small applications or testing.
User details are not persistent; they are lost on application restart.  


JdbcUserDetailsManager:

Implementation of UserDetailsManager in Spring Security.
Stores user details in a relational database using JDBC.
Suitable for larger applications where user details need to be persistent.
User details are stored in database tables.  


UserDetails:

Interface in Spring Security representing user details.
Contains information like username, password, authorities (roles), account status, etc.
Implemented by objects that hold user details, and both InMemoryUserDetailsManager and JdbcUserDetailsManager work with UserDetails objects.
In essence, these components provide different ways of managing user details, either in-memory or through a JDBC-connected database, catering to different application needs and scales.


## Interfaces
##### admin


![Screenshot 2023-11-23 220430](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/62d1b001-55eb-4482-87bd-222580df1896)

![Screenshot 2023-11-23 220757](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/63e003f2-ceeb-4770-b2c2-ce4402bca4e7)
![Screenshot 2023-11-23 220738](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/fba4fcd0-ecf6-4383-9c06-a1cbcc58357f)
![Screenshot 2023-11-23 220642](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/2b97a3bb-853d-4cb0-aecc-2e37659e8e2d)
![Screenshot 2023-11-23 220525](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/337c3ff8-b5b7-49b4-b95f-013db8702738)
![Screenshot 2023-11-23 220514](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/f9579a9d-dc2f-4fe6-b526-cb5410a8a420)
![Screenshot 2023-11-23 220504](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/797ee134-41d1-4a54-b463-d20d0ff1f620)
![Screenshot 2023-11-23 220448](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/aeb1f4b3-2c67-4101-99ee-bfe556828ee1)
![Screenshot 2023-11-23 220440](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/bba6df19-4e27-4c66-9e8a-92f45d7332f6)

##### user

![Screenshot 2023-11-23 220822](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/2b80be52-df9e-4188-9194-0dffa9af7731)
![Screenshot 2023-11-23 220834](https://github.com/abdelilahElgharbaoui/SpringBootSecurityUserDetails/assets/87317250/b0c06911-d265-44c7-9989-070328dc6c46)

