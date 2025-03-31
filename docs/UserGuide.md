---
layout: default.md
title: "User Guide"
pageNav: 3
---

# Wedding Hero User Guide

Wedding Hero is a **desktop application** tailored specifically for wedding planners, optimised for fast
interactions via a **Command Line Interface** (CLI) while still providing the benefits of a clear and intuitive
**Graphical User Interface** (GUI). Designed to help **professional wedding planners** manage complex wedding details
efficiently,
Wedding Hero consolidates **guest lists, seating arrangements, and crucial guest details like dietary information and
RSVP status** into one centralised dashboard. If you’re comfortable typing quickly, Wedding Hero empowers you to manage
your wedding planning tasks more efficiently and precisely than traditional mouse-based applications.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed
    [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).<br>
    **Window and Linux users:** can find their Java `17` download links
    [here](https://www.oracle.com/java/technologies/downloads/#java17).
2. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar`
command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be typed in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Items in square brackets are optional.<br>
  e.g. `deleteGuest [n/NAME] [p/PHONE_NUMBER]` can be used as `deleteGuest [n/NAME]` or `deleteGuest [p/PHONE_NUMBER]`

* Extraneous parameters for commands that do not take in parameters (such as `help`, `weddingOverview`, `exit` and
  `seeRSVPList`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Creating a Wedding : `createWedding`

Creates a new wedding in the wedding planner.

**Format:**
`createWedding WEDDINGNAME`

- Creates a wedding with the provided `WEDDINGNAME`.
- The parameter `WEDDINGNAME` should be a valid string representing the name of the wedding. Feel free to use spaces.
- This command is used to quickly add a new wedding event to the system.

**Examples:**
- Running `createWedding John & Jane Wedding` will create a wedding entry with the name "John & Jane Wedding".
- Using `createWedding Smith Family Wedding` will add a wedding event named "Smith Family Wedding".


### Setting a Wedding : `setWedding`

Sets a specific wedding as the active wedding, enabling modifications such as editing guest lists and other wedding details.

**Format:**
`setWedding WEDDINGNAME`

- Sets the active wedding context to the wedding with the provided **`WEDDINGNAME`**.
- Once set, subsequent commands will target this wedding for modifications.
- The **`WEDDINGNAME`** must match exactly as stored in the system.

**Examples:**
- Running `setWedding John & Jane Wedding` sets the active wedding to "John & Jane Wedding".
- Using `setWedding Smith Wedding` sets the active wedding to the wedding named "Smith Wedding".

### Wedding Overview : `weddingOverview`

Provides an overview of the current active wedding, including details such as the number of tables and guests.

**Format:**
`weddingOverview`

- Retrieves a summary overview of the active wedding.
- No additional arguments are required.
- The overview includes key details such as the number of tables and guests and the list of guests invited.

**Examples:**
- Running `weddingOverview` after setting an active wedding displays the wedding's summary details.


### Adding a person: `addGuest`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DIETARY_RESTRICTION r/RSVP`

<box type="tip" seamless>

💡**Tip:** Refer to the dietary restriction section below to see the full list of dietary restrictions to choose from.

</box>

Examples:
* `addGuest n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES`
* `addGuest n/Willams p/88887777 e/Willams@example.com a/321 Street d/None r/NO`


### Deleting a Person : `deleteGuest`

Deletes the specified guest from the guest list.

**Format:**
`deleteGuest [n/NAME] [p/PHONE_NUMBER]`

- Deletes the guest with the provided **NAME**.
- The parameter `n/` must be followed by the guest's name **exactly** as it appears.
- If you only key in parts of the guest's name, you will get an error.
- **Important**: Although both parameters `n/` and `p/` are optional, you would have to use **only one** of them as
an identifier and **not both**.

**Examples:**
- Running `seeRSVPList` followed by `deleteGuest p/12341234` will delete the guest with the phone number `12341234` from
  the guest list.
- Running `weddingOverview`, taking a look at the guest list, followed by `deleteGuest n/Johnny Wang` deletes the
guest with the name `Johnny Wang`

### Filtering Guests : `filterGuest`

Filters the guest list based on **dietary restrictions**, **RSVP status**, or both.

**Format:**
`filterGuest [d/DIETARY_RESTRICTION] [r/RSVP]`

- Filters guests using the provided dietary restriction and/or RSVP status.
- You **must provide at least one** of the two fields: `d/` for dietary restriction or `r/` for RSVP.
- Parameters can be used individually or together.
- Accepted dietary restrictions include: `None`, `Vegetarian`, `Vegan`, `Gluten-Free`, `Halal`, `Kosher` (see app for full list).
- Accepted RSVP statuses: `YES`, `NO`, `MAYBE`.

**Examples:**
- `filterGuest d/Vegetarian` shows all guests with a vegetarian dietary preference.
- `filterGuest r/YES` shows all guests who RSVP’d "YES".
- `filterGuest d/Vegan r/NO` shows guests who are vegan and RSVP’d "NO".


### Adding a Table : `addTable`

Adds a table with the specified ID and capacity.

**Format:**
`addTable tableID/TABLEID CAPACITY`

- Adds a table with the provided `TABLEID` and `CAPACITY`.
- The parameter `TABLEID` denotes the unique identifier for the table. Ensure that it is unique by using
  the `getTablesCommand`.
- The parameter `CAPACITY` represents the seating capacity of the table.
- Both parameters are required to correctly add the table to the wedding plan.

**Examples:**
- Running `addTable tableID/12 8` will add a table with the ID `12` and a seating capacity for 8 guests.

### Deleting a Table : `deleteTable`

Deletes a table by its ID.

**Format:**
`deleteTable tableID/TABLEID`

- Deletes the table with the provided `TABLEID`.
- The parameter `TABLEID` must match the table's ID exactly as stored in the system.
- This command removes the specified table from the currently set wedding.

**Examples:**
- Running `deleteTable tableID/12` deletes the table with the ID `12`.

### Listing Tables : `getTables`

Lists all tables currently added to the wedding layout.

**Format:**
`getTables`

- Retrieves a list of all tables.
- No additional arguments are required.
- The command displays details of each table like the guests seated at that table for easy reference.

### Finding a Table : `findTable`

Finds a table by its ID.

**Format:**
`findTable tableID/TABLEID`

- Searches for the table with the specified `TABLEID`.
- The parameter `TABLEID` should exactly match the table's identifier number.
- Useful for quickly locating a specific table in the wedding layout.

**Examples:**
- Running `findTable tableID/12` searches for and displays the table with the ID `12`.

### Showing RSVP List : `seeRSVPList`

Displays the RSVP list for the current active wedding.

**Format:**
`seeRSVPList`

- Retrieves and displays the RSVP list of the wedding that is currently set as active.
- No additional arguments are required.
- Useful for quickly reviewing which guests have responded.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.
<box type="warning" seamless><br>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Automatic assignment of guests to tables when guest RSVP status is YES `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

--------------------------------------------------------------------------------------------------------------------


## Command Summary

Action                 | Format, Examples
-----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------
**Help**               | `help`
**Create Wedding**     | `createWedding WEDDINGNAME`<br>e.g., `createWedding John & Jane Wedding`<br>e.g., `createWedding Smith Family Wedding`
**Set Wedding**        | `setWedding WEDDINGNAME`<br>e.g., `setWedding John & Jane Wedding`<br>e.g., `setWedding Smith Wedding`
**Wedding Overview**   | `weddingOverview`
**Add Person**          | `addGuest n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DIETARY_RESTRICTION r/RSVP`<br>e.g., `addGuest n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES`
**Delete Person**       | `deleteGuest [n/NAME] [p/PHONE_NUMBER]`<br>e.g., `deleteGuest p/12341234`<br>e.g.,`deleteGuest n/Johnny Wang` 
**Add Table**          | `addTable tableID/TABLEID CAPACITY`<br>e.g., `addTable tableID/12 8`
**Delete Table**       | `deleteTable tableID/TABLEID`<br>e.g., `deleteTable tableID/12`
**List Tables**        | `getTables`
**Find Table**         | `findTable tableID/TABLEID`<br>e.g., `findTable tableID/12`
**RSVP List**          | `seeRSVPList`
**Exit**               | `exit`
