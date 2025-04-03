---
layout: default.md
title: "User Guide"
pageNav: 3
---

# Wedding Hero User Guide

--------------------------------------------------------------------------------------------------------------------

## About Wedding Hero

**Wedding Hero** is a desktop application designed for **professional wedding planners** who manage large and complex weddings. Whether you're juggling multiple guest lists, dealing with intricate seating plans, or tracking RSVP statuses and dietary preferences across multiple weddings — Wedding Hero helps you stay organised and in control.

Unlike typical mouse-based apps, Wedding Hero combines the speed of a **Command Line Interface (CLI)** with the usability of a **Graphical User Interface (GUI)**. This hybrid design means planners who are fast typists can execute commands in seconds, while still benefiting from an intuitive visual overview of the event.

--------------------------------------------------------------------------------------------------------------------

### Key Features

#### Guest & RSVP Management
- **Contact Details**: Add guests with phone, email, address, dietary needs, and RSVP status
- **RSVP Overview**: Instantly view confirmed and pending guests
- **Guest Filtering**: Filter by RSVP status or dietary restriction

#### Table & Seating Management
- **Table Creation**: Add tables with custom IDs and seating capacity
- **Guest Assignment**: Assign guests to tables within a wedding
- **Table Lookup**: Search for tables and view current seatings

####  Multi-Wedding Planning
- **Wedding Profiles**: Create and manage multiple weddings independently
- **Active Context Switching**: Use `setWedding` to switch between weddings on the fly
- **Wedding Overview**: Summarise guest counts and table setups

#### Built for Speed
- **CLI-First Control**: Execute all commands rapidly through a command box
- **Instant Feedback**: Visual GUI updates for every action

--------------------------------------------------------------------------------------------------------------------
<!-- * Table of Contents -->
<page-nav-print />


## Table of Contents

### Getting Started
- [Quick Start](#quick-start)
- [Glossary](#glossary)
- [Command Input Format & Syntax](#command-input-format--syntax)


### Using Wedding Hero
- [Getting Help](#getting-help)
- [Viewing Help](#viewing-help-help)

### Managing Weddings
- [Creating a Wedding](#creating-a-wedding--createwedding)
- [Setting a Wedding](#setting-a-wedding--setwedding)
- [Wedding Overview](#wedding-overview--weddingoverview)
- [Deleting a Wedding](#deleting-a-wedding--deletewedding)

### Managing People
- [Adding a Person](#adding-a-person-addperson)
- [Deleting a Person](#deleting-a-person--deleteperson)
- [Filtering Persons](#filtering-persons-filterpersons)
- [Viewing RSVP List](#showing-rsvp-list--seersvplist)

### Managing Tables
- [Adding a Table](#adding-a-table--addtable)
- [Deleting a Table](#deleting-a-table--deletetable)
- [Listing Tables](#listing-tables--gettables)
- [Finding a Table](#finding-a-table--findtable)

### Seating Management
- [Assigning a Person to a Table](#adding-a-person-to-a-table-addpersontotable)
- [Removing a Person from a Table](#removing-a-person-from-a-table-deletepersonfromtable)

### Application Commands
- [Exiting the Program](#exiting-the-program--exit)
- [Saving the Data](#saving-the-data)
- [Editing the Data File](#editing-the-data-file)

### Support & Reference
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)


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
- `addPerson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/None r/YES` : Adds a person named John Doe with RSVP status and dietary preference to the active wedding.
- `deletePerson 3` : Deletes the 3rd person shown in the currently displayed person list.
- `exit` : Exits the application.

- Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

##  Command Input Format & Syntax

<box type="info" seamless>

**Notes about the command format:**<br>

- **Words in `UPPER_CASE` are placeholders for user input.**  
  For example, in `add n/NAME`, you can use:  
  `add n/John Doe` (where `John Doe` replaces `NAME`).

- **Prefixes** (e.g., `n/`, `p/`, `e/`) **must be used exactly as shown**, including lowercase letters and the slash.  
  Missing or mistyped prefixes will result in an invalid command.

- **Parameters can be typed in any order.**  
  For example, `n/John p/91234567` and `p/91234567 n/John` are both valid.

- **Commands are case-sensitive for field values** where applicable, such as `d/VEGAN` vs. `d/vegan`.

- **Enclose values in quotes if they include special characters or prefixes.**  
  For example:  
  Use `n/"John p/Doe"` instead of `n/John p/Doe` to avoid parsing errors.

- **Duplicate prefixes will cause the command to fail unless escaped properly using `\`.**  
  For example:  
  `n/John \p/Doe` if a name includes the string `p/`.

- **Optional parameters are shown in square brackets.**  
  e.g., `deletePerson [INDEX]` means you provide just the index like `deletePerson 2`.


⚠️ **PDF Warning**: If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

--------------------------------------------------------------------------------------------------------------------
## Using Wedding Hero

### Getting Help

You can access a help popup at any time while using Wedding Hero.
![help message](images/helpMessage.png)

### Viewing Help: `help`

Displays a message that explains how to access the full user guide.

**Format:**
```plaintext
help
```
## How Wedding Hero Works (System Flow)

Wedding Hero helps you manage **multiple weddings** with ease by using a **“set and operate” model**:

1. **Create a wedding** using the `createWedding` command.
2. **Set the wedding as active** using the `setWedding` command.
3. Once a wedding is set as active, all operations like adding guests, tables, or assigning seats apply **only to that active wedding**.
4. You can view and manage each wedding’s guests, tables, and RSVP details independently.

> 💡 **Note:** You can only interact with one wedding at a time. You must `setWedding` before performing most other commands (e.g., `addGuest`, `addTable`, etc.).

---

### Typical Workflow Example

Here's a typical command sequence you might use a wedding planner:

```bash
createWedding John & Jane Wedding
setWedding John & Jane Wedding
addGuest n/John p/91234567 e/john@example.com a/123 Street d/None r/YES
addTable tableID/1 10
addPersonToTable tableID/1 p/91234567
weddingOverview
```
--------------------------------------------------------------------------------------------------------------------
## Features

### Managing Weddings

--------------------------------------------------------------------------------------------------------------------
### Creating a Wedding : `createWedding`

Creates a new wedding in the wedding planner.

**Format:**
`createWedding n/WEDDINGNAME`

- Creates a wedding with the provided `n/WEDDINGNAME`.
- The parameter `n/WEDDINGNAME` should be a valid string representing the name of the wedding. Feel free to use spaces.
- This command is used to quickly add a new wedding event to the system.

**Examples:**
- Running `createWedding John & Jane Wedding` will create a wedding entry with the name "John & Jane Wedding".
- Using `createWedding Smith Family Wedding` will add a wedding event named "Smith Family Wedding".

<box type="tip" seamless>
💡 Tips:

- After using createWedding, remember to set the wedding as active using:
setWedding John & Jane Wedding
- Always match spacing exactly when setting or referring to a wedding — "John&JaneWedding" is not the same as "John & Jane Wedding". 
- Once a wedding is set, any added persons, tables, or edits will apply to that active wedding.

</box>

### Setting a Wedding : `setWedding`

Sets a specific wedding as the active wedding, enabling modifications such as editing guest lists and other wedding details.

**Format:**
```
`setWedding WEDDINGNAME`
```

- Sets the active wedding context to the wedding with the provided **`WEDDINGNAME`**. 
- Activates a previously created wedding identified by WEDDINGNAME.
-Once set, all person and table operations will apply to this active wedding.
-The WEDDINGNAME must match the name used during createWedding, with correct spacing.


**Examples:**
- Running `setWedding John & Jane Wedding` sets the active wedding to "John & Jane Wedding".
- Using `setWedding Smith Wedding` sets the active wedding to the wedding named "Smith Wedding".


  <box type="info" seamless> 📌 Note: You can only modify or view wedding details *after* setting a wedding as active. </box>

### Wedding Overview : `weddingOverview`

Provides an overview of the current active wedding, including details such as the number of tables and guests.

**Format:**
```
`weddingOverview`
```

- Retrieves a summary overview of the active wedding.
- No additional arguments are required.
- The overview includes key details such as the number of tables and guests and the list of guests invited.

**Examples:**
- Running `weddingOverview` after setting an active wedding displays the wedding's summary details.

### Deleting a Wedding : `deleteWedding`

Deletes a wedding from the system by name.

**Format:**
```
deleteWedding WEDDINGNAME
```

- Permanently deletes the wedding identified by WEDDINGNAME.
- WEDDINGNAME must match the exact name of the wedding you created. The comparison is case-insensitive but spacing must match exactly.
- Once deleted, all associated persons, tables, and data under the wedding will also be removed.

Examples:
- deleteWedding John & Jane Wedding would delete John & Jane Wedding

<box type="warning" seamless> ⚠️ Warning: This action is irreversible. Ensure you are deleting the correct wedding before confirming. </box>

--------------------------------------------------------------------------------------------------------------------

## Managing People

### Adding a person: `addPerson`

Adds a person to the currently active wedding’s guest list.

**Format:**
```
`addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DIETARY_RESTRICTION r/RSVP`
```
Details:

All fields are mandatory and must follow the exact format.

Each prefix represents a specific attribute of the person:
- n/ → Full name (e.g., John Doe)
- p/ → Phone number (e.g., 12345678)
- e/ → Email address (e.g., johndoe@example.com)
- a/ → Address (e.g., 123 Street)
- d/ → Dietary restriction (must be selected from approved list below)
- r/ → RSVP status (YES or NO, NO_RESPONSE)

<box type="tip" seamless>

💡**Tip:** Refer to the dietary restriction section below to see the full list of dietary restrictions to choose from.

</box>

Allowed d/DIETARY_RESTRICTION values:
- None
- Vegetarian
- Vegan
- HALAL
- SHELLFISH
- PEANUTS
- EGGS
- FISH
- SOY
- SESMAE
- NONE

Allowed RSVP values:
- YES
- NO
- NO_RESPONSE (if not yet responded)
**Examples:**
```
addPerson n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES
addPerson n/Alex Tan p/87654321 e/alex@example.com a/456 Avenue d/Vegan r/NO
```

### Deleting a Person : `deletePerson`

Deletes a person from the currently active wedding's guest list, using their displayed index number.


**Format:**
```
`deletePerson [INDEX]`
```
Details:
INDEX: A positive integer corresponding to the person's number in the displayed person list.
(e.g., from a previous list or filterPersons command)

- You must run setWedding before this command — deletion only works for persons in the currently active wedding.
- If the index is invalid or out of range, the system will show an error.
- Deletion is permanent and cannot be undone.

**Examples:**
```
deletePerson 1     // Deletes the first person shown in the list
deletePerson 3     // Deletes the third person shown in the list
```
<box type="warning" seamless> ❗ Make sure you're viewing the correct list of persons before deleting — the index is based on the currently displayed list. </box>

### Filtering Persons: `filterPersons`

This command allows you to filter your list of persons by applying dietary restriction and/or RSVP status filters. 
You can use it to display only those persons who meet the criteria you specify.

**Format:**
```
`filterPersons [d/DIETARYRESTRICTION] [r/RSVP]`
```
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

### Showing RSVP List : `seeRSVPList`

Displays the RSVP list for the current active wedding.

**Format:**
`seeRSVPList`

- Retrieves and displays the RSVP list of the wedding that is currently set as active.
- No additional arguments are required.
- Useful for quickly reviewing which guests have responded.
- 
## Managing Tables

--------------------------------------------------------------------------------------------------------------------

### Adding a Table : `addTable`

Adds a table with the specified ID and capacity to the current wedding

**Format:**
```
`addTable tid/TABLEID c/CAPACITY`
```

- Adds a table with the provided `TABLEID` and `CAPACITY`.
- The parameter `TABLEID` denotes the unique integer identifier for the table. Ensure that it is unique by using
  the `getTablesCommand`, to view the other tables
- The parameter `CAPACITY` represents the seating capacity of the table.
- Both parameters are required to correctly add the table to the wedding plan.

**Examples:**
- Running `addTable tid/12 c/8` will add a table with the ID `12` and a seating capacity for 8 guests.

### Deleting a Table : `deleteTable`

Deletes a table by its ID.

**Format:**
`deleteTable tid/TABLEID`

- Deletes the table with the provided `TABLEID`.
- The parameter `TABLEID` must match the table's ID exactly as stored in the system.
- This command removes the specified table from the currently set wedding.

**Examples:**
- Running `deleteTable tid/12` deletes the table with the ID `12`.

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
`findTable tid/TABLEID`

- Searches for the table with the specified `TABLEID`.
- The parameter `TABLEID` should exactly match the table's identifier number.
- Useful for quickly locating a specific table in the wedding layout.

**Examples:**
- Running `findTable tid/12`  searches and displays the table with the id 12.

### Showing RSVP List : `seeRSVPList`

Displays the RSVP list for the current active wedding.

**Format:**
`seeRSVPList`

- Retrieves and displays the RSVP list of the wedding that is currently set as active.
- No additional arguments are required.
- Useful for quickly reviewing which guests have responded.

## Assigning People to Tables

--------------------------------------------------------------------------------------------------------------------
### Adding a Person to a Table: addPersonToTable
Assigns a person to a specific table within a currently active wedding

**Format:**
```
addPersonToTable n/NAME tid/INDEX  
```
- Searches for the table with the specified `TABLEID`.
- The parameter `TABLEID` should exactly match the table's identifier number.
- Useful for quickly locating a specific table in the wedding layout.

**Examples:**
- Running `findTable tid/12`  searches and displays the table with the id 12.

### Removing a Person from a Table: deletePersonFromTable
Removes a person from a table in the currently active wedding.

**Format**:
```
deletePersonFromTable n/NAME tid/INDEX  
```
- deletePersonFromTable n/PERSON_NAME tid/TABLEID
- PERSON_NAME is the name of the person displayed on the list

example:
- deletePersonFromTable name/John Doe tid/5 deletes the John Doe person from the table with ID 5

--------------------------------------------------------------------------------------------------------------------

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

WeddingHero data are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

WeddingHero data are saved automatically as a JSON file `[JAR file location]/data/WeddingHero.json`. Advanced users are welcome to update data directly by editing that data file.
<box type="warning" seamless><br>

**Caution:**
If your changes to the data file makes its format invalid, WeddingHero will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the WeddingHero to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>
--------------------------------------------------------------------------------------------------------------------
### Glossary

- **Active Wedding**: The currently selected wedding that all actions (e.g., adding people or tables) apply to. You must use the `setWedding` command to set an active wedding.

- **Table ID**: A unique integer identifier (e.g., `1`, `5`, `12`) given to each table during the `addTable` command. Used for assigning and locating tables.

- **Dietary Restriction**: Describes food requirements or allergies of a person. Acceptable values include: `None`, `Vegetarian`, `Vegan`, `HALAL`, `SHELLFISH`, `PEANUTS`, `FISH`, `EGGS`, `SOY`, `SESAME`.

- **RSVP**: Indicates whether a person has responded to an invitation. Valid values: `YES`, `NO`, `NO_RESPONSE`.

- **Index**: A positive integer shown in the GUI list view that represents the position of a person in the current filtered or full list. Used in commands like `deletePerson`.


--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WeddingHero home folder.

--------------------------------------------------------------------------------------------------------------------
## Known issues
1. When using multiple screens, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the preferences.json file created by the application before running the application again.
2. If you minimize the Help Window and then run the help command (or use the Help menu, or the keyboard shortcut F1) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------
## Planned Enhancements




--------------------------------------------------------------------------------------------------------------------
## Command Summary

| Action                        | Format, Examples                                                            |
|-------------------------------|-----------------------------------------------------------------------------|
| **Help**                      | `help` <br> e.g., `help`                                                    |
| **Create Wedding**            | `createWedding WEDDINGNAME` <br> e.g., `createWedding John & Jane Wedding` |
| **Set Wedding**               | `setWedding WEDDINGNAME` <br> e.g., `setWedding John & Jane Wedding`       |
| **Wedding Overview**          | `weddingOverview` <br> e.g., `weddingOverview`                             |
| **Add Guest**                 | `addGuest n/NAME p/PHONE e/EMAIL a/ADDRESS d/DIETARY r/RSVP` <br> e.g., `addGuest n/Ana p/9123...` |
| **Delete Guest**              | `deleteGuest n/NAME` or `deleteGuest p/PHONE_NUMBER` <br> e.g., `deleteGuest p/91234567` |
| **Filter Guests**             | `filterPersons [d/DIETARY] [r/RSVP]` <br> e.g., `filterPersons d/VEGETARIAN r/YES` |
| **RSVP List**                 | `seeRSVPList` <br> e.g., `seeRSVPList`                                     |
| **Add Table**                 | `addTable tableID/TABLEID CAPACITY` <br> e.g., `addTable tableID/5 10`     |
| **Delete Table**              | `deleteTable tableID/TABLEID` <br> e.g., `deleteTable tableID/5`           |
| **List All Tables**           | `getTables` <br> e.g., `getTables`                                         |
| **Find Table by ID**          | `findTable tableID/TABLEID` <br> e.g., `findTable tableID/5`               |
| **Assign Guest to Table**     | `addPersonToTable tid/TABLEID, `addPersonToTable tableID/5 p/91234567`     |
| **Remove Guest from Table**   | `deletePersonFromTable tableID/TABLEID p/PHONE_NUMBER` <br> e.g., `deletePersonFromTable tableID/5 p/91234567` |
| **Exit the Program**          | `exit` <br> e.g., `exit`                                                    |
