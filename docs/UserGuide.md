---
layout: default.md
title: "User Guide"
pageNav: 3
---

# Wedding Hero User Guide

As a wedding planner, you need a tool that keeps pace with your fast-moving schedule. 
**Wedding Hero** is a desktop application tailored specifically for you. Optimised for rapid interactions through a 
**Command Line Interface (CLI)**—while still offering the clarity and ease of a **Graphical User Interface (GUI)**
—this application is built to help you manage complex wedding details with precision.

## Key Features

- **Guest Lists & Seating Arrangements:** Organise and modify with speed.
- **Crucial Guest Details:** Manage contact information, dietary requirements and RSVP statuses seamlessly.
- **Multiple Weddings Management:** Keep track of several weddings effortlessly, consolidating details for each event 
in one centralised dashboard.

If you're comfortable typing quickly, Wedding Hero empowers you to execute your planning tasks more efficiently than 
traditional mouse-based applications.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed
    [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).<br>
    **Window and Linux users:** can find their Java `17` download links
    [here](https://www.oracle.com/java/technologies/downloads/#java17).
2. Download the latest `.jar` file from [here](https://github.com/se-edu/WeddingHero-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your WeddingHero.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar WeddingHero.jar`
command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
open the help window.<br>
   Some example commands you can try:

   * `createWedding n/ Jack and Jill's Wedding` : Creates a new wedding.
   
   * `weddingOverview`: Displays a overview of the wedding, including the number of tables, guests and a list of 
     persons attending.

   * `addPerson n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES` : Adds a person named `John Doe` 
     to the currently set wedding.

   * `delete 3` : Deletes the 3rd person shown in the currently selected wedding.

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

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple 
  lines as space characters surrounding line-breaks may be omitted when copied over to the application.

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

Sets a specific wedding as the active wedding, enabling modifications such as editing guest lists and other wedding 
details.

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


### Adding a person: `addPerson`

Adds a person to the currently selected wedding.

**Format**: `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DIETARY_RESTRICTION r/RSVP`

<box type="tip" seamless>
💡**Tip:** Refer to the dietary restriction section below to see the full list of dietary restrictions to choose from.
</box>

- A wedding should be set before a person can be added.

Examples:
* `addPerson n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES`
* `addPerson n/Willams p/88887777 e/Willams@example.com a/321 Street d/None r/NO`


### Deleting a Person : `deletePerson`

Deletes the a person from the currently selected wedding.

**Format:**
`deletePerson INDEX`

- Deletes the guest with the provided **NAME**.
- The parameter `n/` must be followed by the guest's name **exactly** as it appears.
- If you only key in parts of the guest's name, you will get an error.
- **Important**: Although both parameters `n/` and `p/` are optional, you would have to use **only one** of them as
an identifier and **not both**.

**Examples:**
- deleteGuest 

### Filtering Persons: `filterPersons`

This command allows you to filter your list of persons by applying dietary restriction and/or RSVP status filters. 
You can use it to display only those persons who meet the criteria you specify.

**Format:** 
`filterPersons [d/DIETARYRESTRICTION] [r/RSVP]`

- Both parameters are optional for this command.
- **Dietary Restriction Filter:** Use the prefix `d/` followed by a valid dietary restriction value 
(e.g., `VEGAN`, `VEGETARIAN`). Include this if you want to filter persons based on dietary needs.
- **RSVP Filter:** Use the prefix `r/` followed by a valid RSVP status (e.g., `YES`, `NO`). 
Include this if you want to filter persons by their RSVP response.

You can provide one or both filters to narrow down your list. If no filters are specified, 
the command returns an unfiltered list of persons belonging to that wedding.

![List of filtered persons based on RSVP - No](images/UG-example-images/filterPersonExample.png)
<sub>Example output when filtering persons based on RSVP `NO` using command: `filterPersons r/NO`</sub>

**Examples:**
- Running `filterPersons d/VEGAN r/YES` displays all persons who are vegan and have accepted the invitation.
- Using `filterPersons d/HALAL` displays all persons with a halal dietary restriction.
- Running `filterPersons` displays all persons without any filter.

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

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

WeddingHero data are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

WeddingHero data are saved automatically as a JSON file `[JAR file location]/data/WeddingHero.json`. 
Advanced users are welcome to update data directly by editing that data file.
<box type="warning" seamless><br>

**Caution:**
If your changes to the data file makes its format invalid, WeddingHero will discard all data and start with an 
empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the WeddingHero to behave in unexpected ways (e.g., if a value entered is outside 
the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Automatic assignment of guests to tables when guest RSVP status is YES `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous WeddingHero home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues
No known issues.
--------------------------------------------------------------------------------------------------------------------


## Command Summary

| **Action**                | **Format, Examples**                                                                                                                                                 |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **createWedding**         | `createWedding n/NAME`<br>Example: `createWedding n/John & Jane Wedding`                                                                                             |
| **deleteWedding**         | `deleteWedding n/NAME`<br>Example: `deleteWedding n/John & Jane Wedding`                                                                                             |
| **setWedding**            | `setWedding n/NAME`<br>Example: `setWedding n/Smith Wedding`                                                                                                         |
| **weddingOverview**       | `weddingOverview`<br>Example: `weddingOverview`                                                                                                                      |
| **addPerson**             | `addPerson n/NAME p/PHONE e/EMAIL a/ADDRESS d/DIETARY_RESTRICTION r/RSVP`<br/>Example: `addPerson n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/Vegan r/YES` |
| **deletePerson**          | `deletePerson INDEX`<br>Example: `deletePerson 3`                                                                                                                    |
| **Find**                  | `Find KEYWORD`<br>Example: `Find John`                                                                                                                               |
| **filterPersons**         | `filterPersons [d/DIETARY_RESTRICTION_FIELD] [r/RSVP_FIELD]`<br>Example: `filterPersons d/Vegan r/YES`                                                               |
| **addTable**              | `addTable tid/TABLE_ID c/CAPACITY`<br>Example: `addTable tid/1 c/8`                                                                                                  |
| **addPersonToTable**      | `addPersonToTable n/NAME tid/TABLE_ID`<br>Example: `addPersonToTable n/John Doe tid/1`                                                                               |
| **deletePersonFromTable** | `deletePersonFromTable n/NAME tid/TABLE_ID`<br>Example: `deletePersonFromTable n/John Doe tid/1`                                                                     |
| **deleteTable**           | `deleteTable tid/TABLE_ID`<br>Example: `deleteTable tid/1`                                                                                                           |
| **findTable**             | `findTable [tid/TABLE_ID] [INDEX]`<br>Examples: `findTable tid/1` or `findTable 1`                                                                                   |
| **getTables**             | `getTables`<br>Example: `getTables`                                                                                                                                  |
| **Help**                  | `Help`                                                                                                                                                               |
| **exit**                  | `exit`                                                                                                                                                               |
