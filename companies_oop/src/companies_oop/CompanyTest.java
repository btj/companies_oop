package companies_oop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class CompanyTest {

	@Test
	void test() {
		Company alphabet = new Company();
		assertNull(alphabet.getOwner());
		assertEquals(Set.of(), alphabet.getOwnedCompanies());
		
		Company google = new Company();
		alphabet.takeOwnershipOf(google);
		assertSame(alphabet, google.getOwner());
		assertEquals(Set.of(google), alphabet.getOwnedCompanies());
		
		alphabet.relinquishOwnershipOf(google);
		assertNull(google.getOwner());
		assertEquals(Set.of(), alphabet.getOwnedCompanies());
	}

}
