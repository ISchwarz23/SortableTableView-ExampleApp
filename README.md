# SortableTableView - Example App
An example App that shows how the [SortableTableView](http://www.sortabletableview.com) can be used. The App contains the 
following examples:
- rendering simple data
- rendering custom data
- dynamic loading of data
- searching data
- sorting data
- handle selection

The example App can be downloaded from the Google PlayStore.  
[![Download Example App](http://www.clintonfitch.com/wp-content/uploads/2015/06/Google-Play-Button.jpg)](https://play.google.com/store/apps/details?id=com.sortabletableview.recyclerview.exampleapp)

### General Remark
In all examples you can see how to use a `TableDataAdapter` to show your data inside the table. Also all examples
use a `TableColumnModel` to specify the width of each column. This `TableColumnModel` is also used to show a different
number of columns depending if the device is in portrait or landscape mode.

### Rendering Simple Data
In this example you can see how to use the `SimpleTableDataColumnAdapter` to render your data. The advantage of this 
adapter is, that you can show your data in a table in seconds.  
In addition this example shows how to style the header, show row dividers and column dividers.

### Rendering Custom Data
In this example you can see how to implement your own `TableDataColumnAdapter` to render your data. The advantage of
implementing your own `TableDataColumnAdapter` is, that there is no limitation as you can render custom views.  
In addition this example shows how to do row background styling.

### Dynamic Loading of Data
In this example you can see how to use the build in Swipe-to-Refresh feature to reload data. It also shows how to style
the Swipe-to-Refresh loading indicator.  
In addition this example shows how to display an empty data indicator view when there is no data available in the adapter.

### Searching Data
In this example you can see how to implement a search and display the result inside the table.

### Sorting Data
In this example you can see how to make the data sortable by the user by setting a `Comparator` for the columns. It also
shows how to style the sorting order state view.

### Handle Selection
In this example you can see how to add selection to your table using the `SelectionHelper` and how this selection
highlighting can be customized.