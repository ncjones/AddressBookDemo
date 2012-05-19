using AddressBookDemo;

public class AddressBookDemoMain : Object {
	
	public static int main (string[] args) {
		ContactService contactService = new InMemoryContactService();
		foreach (var contact in contactService.GetAllContacts()) {
			stdout.printf("%s\n", contact.Name);
		}
		return 0;
	}

}
