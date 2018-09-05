# Technical Assessment
## Technologies used
Backend: Java, Hibernate, Liquibase

Fronted: VueJs, Vue-CLI, Element UI

## Requirements
### Customer
1. Unique customer identifier.
2. Status: one of "prospective", "current" or "non-active".
3. Creation date and time.
4. General information like name and contact details.

### Editability
1. Filter the list of customers. (I only implemted a simple filter on the status)
2. Sort the list of customers
3. Click on a customer in the list to view their details
4. Change their status.
5. Add notes for a customer
6. Edit notes for a customer

## How to run it?

You can run `maven install` and then start the jar by using `java -jar propellerhead-assessment-backend-0.0.1-SNAPSHOT.jar`. It is also possible to start the backend in your IDE and starting the frontent using `yarn serve`. This will enable a hotreload.

## Out of scope
As I had to work through the weekend I hardly had time for this assessment. I am aware that there are no frontend tests and that there are only a view tests in the backend. However I thought it would be nice to have an application meeting the requirements so I focused on functionality and not on tests.

I only implemented a simple filter for the status. First I was thinking of building a search for the table but as time was critical I just did the filter on the status. Probably a good filter would be a range filter on the created column. 

There is no pagination on the table. However the usual usecase would be that there are so many customers that you would need pagination or an other style lazy loading from the server (e.g. endless scrolling with fetching data while scrolling).



## Next steps

1. Write frontend tests.
2. I would like to split the 2 frontend views into smaller components e.g. a notes component.
