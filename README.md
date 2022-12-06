# library-management-system

## System Requirements:
1. Any library member should be able to search books by their title, author and category.
2. Each book will have a unique identification number and other details including a rack number which will help to physically locate the book.
3. There could be more than one copy of a book, and library members should be able to borrow and reserve any copy.
4. The system should be able to retrieve information like who took a particular book or what are the books checked-out by a specific library member.
5. There should be a maximum limit (5) on how many books a member can check-out.
6. There should be a maximum limit (14) on how many days a member can keep a book.
7. The system should be able to collect fines for books returned after the due date (0.75$ per day per item). That member cannot borrow anything until the book is returned and the fine is paid.
8. The system should be able to send notifications whenever the reserved books become available, as well as when the book is not returned within the due date.
9. Each book will have a unique barcode. The system will be able to read barcodes from books.
10. Librarians can view status or all book items of the same isbn, and can update the status of the book item.
11. Prohibited members cannot borrow any book.

## ERD:
<img src="D:\SpringBootProjects\diagrams\library-management-system\ERD-ERD.drawio.png"/>
<img src="D:\SpringBootProjects\diagrams\library-management-system\ERD-Relational Table.drawio.png"/>

## Use Goal Use Case:
<img src="D:\SpringBootProjects\diagrams\library-management-system\Use Case Diagram.drawio.png"/>

## Activity Diagrams:
### ✔️Book Borrowing:
<img src="D:\SpringBootProjects\diagrams\library-management-system\Activity Diagram-Borrow book.drawio.png"/>

### ✔️Book Returning:
<img src="D:\SpringBootProjects\diagrams\library-management-system\Activity Diagram-Return book.drawio.png"/>

### ✔️Book Reservation:
<img src="D:\SpringBootProjects\diagrams\library-management-system\Activity Diagram-Reserve book.drawio.png"/>
