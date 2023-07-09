<html>
    <head>
        <title>Create a New Ticket</title>
    </head>
    <body>
        <h2>Create a ticket</h2>
        <form action="ticket" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="create">
            Name:<input type="text" name="name"><br>
            Subject:<input type="text" name="subject"><br>
            Issue:<input type="text" name= "body"><br>
            <input type="file" name="file1"><br>
            <input type="submit">
        </form>
    </body>
</html>