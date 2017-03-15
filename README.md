# BusPooling

### Zalogowanie sie
curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password01&username=rafal@pydyniak.pl&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"

### Dodanie uzytkownika
POST na /api/users
{
    "username":"rafal@pydyniak.pl",
    "password":"password01",
    "birthdate": "01032001",
    "gender": "male",
	"culinaryPreferences": ["fast food", "Italian"],
	"interests": ["football", "dogs"],
	"active": "false"
}

### Pobranie uzytkownikow
GET na /api/users

### Pobranie uzytkownika
GET na /api/users/{id}

### Update użytkownika
PUT na /api/users/{id}
{
    "username":"rafal@pydyniak.pl",
    "password":"password01",
    "birthdate": "01032001",
    "gender": "male",
	"culinaryPreferences": ["fast food", "Italian"],
	"interests": ["football", "dogs"],
	"active": "true"
}

### Usunięcie użytkownika
DELETE na /api/users/{id}
