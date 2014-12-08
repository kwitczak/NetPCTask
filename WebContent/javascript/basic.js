/**
 * 
 */


$(document).ready(function(){
	// Logowanie
	$('#updateUsername').submit(function(){
		$.ajax({
			url: 'update',
			type: 'POST',
			dataType: 'json',
			data: $('#updateUsername').serialize(),
			success: function(data){
				if(data.isValid){
					$('#displayName').html("Logowanie zakończone sukcesem. Witaj " + data.username + "!");
					$('#displayName').slideDown(800);
					$('#editButton').slideDown(800);
					$('#addButton').slideDown(800);
					$('#logoutButton').slideDown(800);
					$('#deleteButton').slideDown(800);
					document.getElementById("llogin").value = "";
					document.getElementById("lpassword").value = "";
				}
				else{
					if (data.login){
						alert("Wykryto niepoprawne znaki w twoim loginie. Uzywaj tylko znaków alfanumerycznych.");
					} else if (data.password) {
						alert("Wykryto niepoprawne znaki w twoim haśle. Uzywaj tylko znaków alfanumerycznych.");
					} else {
						alert('Błędny login i/lub hasło!');
					}
					
				}
			}
		});
		return false;
	});
	// Odczyt danych z listy
	$('#clientList').change(function(){
		var e = document.getElementById("clientList");
		var listItem = e.options[e.selectedIndex].value;
		$.ajax({
			url: 'list',
			type: 'POST',
			dataType: 'json',
			data: {"itemFromList":listItem},
			success: function(data){
				document.getElementById("TBname").value = data.name;
				document.getElementById("TBemail").value = data.email;
				document.getElementById("TBsurname").value = data.surname;
				document.getElementById("TBbirth").value = data.dateofbirth;
				document.getElementById("TBphone").value = data.phonenumber;
				e.options[e.selectedIndex].value = data.listItem;
				}
		});		
	});
	$('#editButton').click(function(){
		var e = document.getElementById("clientList");
		var edited = e.options[e.selectedIndex].value;
		var clientName = document.getElementById("TBname").value;
		var clientSurname= document.getElementById("TBsurname").value;
		var clientPhone = document.getElementById("TBphone").value;
		var clientEmail = document.getElementById("TBemail").value;
		var clientBirth = document.getElementById("TBbirth").value;
		$.ajax({
			url: 'edit',
			type: 'POST',
			dataType: 'json',
			data: {"name":clientName,"surname":clientSurname,
				"phonenumber":clientPhone,"email":clientEmail,"birthdate":clientBirth, "edited":edited},
			success: function(data){
				if ((data.blankField=="0") && (data.emailExists=="0")){
					e.options.remove(e.selectedIndex)
					e.options.add(new Option(clientEmail + " - " + clientName + " " + clientSurname, 
							clientEmail + " - " + clientName + " " + clientSurname));
					e.selectedIndex = e.length-1;
				} else if(data.emailExists!="0" && data.blankField=="0") {
					alert("Email ktory próbujesz wpisać, juz istnieje na liscie! Wybierz inny.")
				} else {
					alert("Wypelnij puste pola!");
				}
			}

		});		

	});
	$('#addButton').click(function(){
		var e = document.getElementById("clientList");
		var edited = e.options[e.selectedIndex].value;
		var clientName = document.getElementById("TBname").value;
		var clientSurname= document.getElementById("TBsurname").value;
		var clientPhone = document.getElementById("TBphone").value;
		var clientEmail = document.getElementById("TBemail").value;
		var clientBirth = document.getElementById("TBbirth").value;
		$.ajax({
			url: 'add',
			type: 'POST',
			dataType: 'json',
			data: {"email":clientEmail, "name":clientName,"surname":clientSurname,
				"phonenumber":clientPhone,"birthdate":clientBirth, "edited":edited},
			success: function(data){
				if ((data.blankField=="0") && (data.emailExists=="0")){
					e.options.add(new Option(clientEmail + " - " + clientName + " " + clientSurname, 
							clientEmail + " - " + clientName + " " + clientSurname));
					e.selectedIndex = e.length-1;
				} else if (data.emailExists != "0"){
					alert("Email " + clientEmail + " juz istnieje - wybierz inny.");
				}
			}
		});		

	});
	$('#deleteButton').click(function(){
		var e = document.getElementById("clientList");
		var edited = e.options[e.selectedIndex].value;
		var r = confirm("Czy na pewno chcesz usunąć z bazy danych rekord pod emailem " + edited + "? Zmiana jest nieodwracalna.");
		if (r == false) {
		    return
		}
		$.ajax({
			url: 'delete',
			type: 'POST',
			dataType: 'json',
			data: {"edited":edited},
			success: function(data){
					e.options.remove(e.selectedIndex)
					e.selectedIndex = e.length-1;
					document.getElementById("TBname").value = "";
					document.getElementById("TBsurname").value = "";
					document.getElementById("TBphone").value = "";
					document.getElementById("TBemail").value = "";
					document.getElementById("TBbirth").value = "";

			}
		});		

	});
	$('#logoutButton').click(function(){
		$.ajax({
			url: 'logout',
			type: 'POST',
			dataType: 'json',
			success: function(data){
				$('#displayName').html("");
				$('#displayName').hide();
				$('#editButton').hide();
				$('#addButton').hide();
				$('#logoutButton').hide();
				$('#deleteButton').hide();

			}
		});		

	});
});