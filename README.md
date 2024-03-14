# TeachMeSkills_C27_Final_Assignment


The program is used to read information from a folder and calculate statistics on invoices, orders and checks. You can log in to the program using your username and password. If the data is entered correctly, the session time is returned to the client.\
The class `StorageMock` stores the user's username and password in encoded form.\
The class `DataEncoding` contains 3 methods: *code* is used to encode the username and password,*decode* is used to decode the username and password, *addSalt* is used to add 10 random characters to the username and password.\
The class `Session` contains 3 methods: *isSessionAlive* to check if the session is valid, *setAccessToken* for generation acess token, *setExpDate* for establish the validity time of the session.\
The class `AuthService` contains 1 method: auth. The method auth is used to authorize the user. If the username and password have been verified, the object of the class Session is returned.\
The class `FileProcessingService` contains 4 methods: *processFile* is public method to verify the validity of the session. If the session is valid, then the following 2 methods are called: *searchFolderFiles* and *checkValidFiles*.\
The method *searchFolderFiles* is used to search in the source folder "data" for files with a name suitable for the standards. The method writes all valid files to the collection fileList. All invalid files are written to a separate folder "invalid_files".\
The method *checkValidFiles* to read information from valid files and search for the line where the turnover in the current month is written.\
As a result of executing this method, the methods are called *calculateTurnover* from another class and *getStatisticsPathValidator* from the class `FileProcessingService`.\
The class `StatisticsService` contains 2 methods: *calculateTurnover* to calculate the turnover separately for each type of file and record statistics in the appropriate file and *calculateTotalTurnoverOnAllFiles* to calculate the total turnover for all types of files and record statistics in the appropriate file. In the method *calculateTurnover* calls the method *convertCurrency* from the class `CurrencyConversionService`
The method *convertCurrency* is used to convert euros and pounds into dollars.\
At each stage of the program, logs of the info type and error type are recorded.
