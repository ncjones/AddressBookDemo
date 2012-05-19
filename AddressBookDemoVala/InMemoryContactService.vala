
namespace AddressBookDemo {
	
	public class InMemoryContactService : Object, ContactService {
		
		public Contact[] GetAllContacts() {
			Contact contact1 = new Contact();
			contact1.Name = "Test One";
			Contact contact2 = new Contact();
			contact2.Name = "Test Two";
			Contact[] contacts = new Contact[]{
				contact1,
				contact2
			};
			return contacts;
		}
	}
}
