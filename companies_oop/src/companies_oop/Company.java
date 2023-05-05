package companies_oop;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * Each instance of this class represents a company in a company ownership graph.
 * 
 * @invar | getOwnedCompanies().stream().allMatch(c -> c.getOwner() == this)
 * @invar | getOwner() == null || this.getOwner().getOwnedCompanies().contains(this)
 */
public class Company {
	
	/**
	 * @invar | ownedCompanies != null
	 * @invar | ownedCompanies.stream().allMatch(c -> c != null)
	 * @invar | owner == null || owner.ownedCompanies.contains(this)
	 * @invar | ownedCompanies.stream().allMatch(c -> c.owner == this)
	 * 
	 * @peerObject
	 */
	private Company owner;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	private HashSet<Company> ownedCompanies = new HashSet<>();
	
	/**
	 * @peerObject
	 */
	public Company getOwner() { return owner; }
	
	/**
	 * @creates | result
	 * @peerObjects
	 * @post | result != null
	 * @post | result.stream().allMatch(c -> c != null)
	 */
	public Set<Company> getOwnedCompanies() { return Set.copyOf(ownedCompanies); }
	
	/**
	 * @post | getOwner() == null
	 * @post | getOwnedCompanies().isEmpty()
	 */
	public Company() {}
	
	/**
	 * @pre | other != null
	 * @pre | other.getOwner() == null
	 * @mutates_properties | this.getOwnedCompanies(), other.getOwner()
	 * @post | other.getOwner() == this
	 * @post | this.getOwnedCompanies().equals(LogicalSet.plus(old(this.getOwnedCompanies()), other))
	 */
	public void takeOwnershipOf(Company other) {
		other.owner = this;
		ownedCompanies.add(other);
	}

	/**
	 * @pre | other != null
	 * @pre | other.getOwner() == this
	 * @mutates_properties | this.getOwnedCompanies(), other.getOwner()
	 * @post | other.getOwner() == null
	 * @post | this.getOwnedCompanies().equals(LogicalSet.minus(old(this.getOwnedCompanies()), other))
	 */
	public void relinquishOwnershipOf(Company other) {
		other.owner = null;
		ownedCompanies.remove(other);
	}
	
}
