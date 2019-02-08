<!-- tes3.php -->

<html>
	<head>
		<title>
			Welcome to INFSCI 2710
		</title>
	</head>
	<body>
		Hello, World!
		<?php
			$servername = "localhost";
			$username = "jip45";
			$password = "woshiwo";

			// Create connection
			$conn = new mysqli($servername, $username, $password);

			// Check connection
			if ($conn -> connect_error) {
				die("Connection failed: " . $conn -> connect_error);
			}
			echo "<p><font color=\"red\">Connected successfully</font></p>";

			// Run a sql
			$sql = "show database;";
			$result = $conn -> query($sql);
			while($row = $result -> fetch_assoc()) {
				echo $row["Database"]."<br/>";
			}
			$result -> free();

			// Close conncection
			mysqli_close($conn);
		?>
	</body>
</html>
