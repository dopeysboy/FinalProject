package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;

public interface DebtService {

	/**
	 * Given a User object, will return a List of all debts owned by that User
	 * @param user - The User you are searching for debts
	 * @return List of all Debts owned by the passed User
	 * @author tylertanner
	 */
	List<Debt> getDebtsByUser(User user);

	/**
	 * Given an id, will return the Debt associated with that id if it is owned by
	 * the passed User
	 * @param id - the id of the Debt
	 * @param user - the User who owns the Debt
	 * @return Either null or the persisted Debt
	 * @author tylertanner
	 */
	Debt getDebtById(int id, User user);
	/**
	 * Given a DebtLender and a User, will return each Debt that has the given User and DebtLender
	 * @param debtLender - The DebtLender object you want to see Debts from
	 * @param user - The User whose Debts you want to see
	 * @return A list of all Debts owned by the User passed that are from the given DebtLender
	 * @author tylertanner
	 */
	List<Debt> getDebtsByDebtLender(DebtLender debtLender, User user);

	/**
	 * Given a Double APR and a User, will return each Debt with that APR owned by the User
	 * @param apr - The Double percentage monthly APR
	 * @param user - The User you want to see Debts for
	 * @return List of all Debts that match the criteria
	 * @author tylertanner
	 */
	List<Debt> getDebtsByApr(Double apr, User user);

	/**
	 * Given a Debt and a User, will set the Debt's User to the passed User and will persist the Debt in the database
	 * @param debt - The new Debt you want persisted
	 * @param user - The User who owns the Debt
	 * @return The saved Debt
	 * @author tylertanner
	 */
	Debt createDebt(Debt debt, User user);

	/**
	 * Will return false given the following conditions:
	 * (1) The User does not own the Debt
	 * (2) The database still contains the Debt after debtRepo.delete()
	 * @param debt - The Debt you want deleted
	 * @param user - The User who owns the Debt
	 * @return True or False based on whether the operation was successful
	 * @author tylertanner
	 */
	boolean destroyDebt(Debt debt, User user);

	/**
	 * Given a newDebt, a debtId, and a User, will update the current Debt found
	 * by debtId to the newDebt as long as: (1) debtId finds a valid Debt and (2)
	 * the passed User owns the old Debt
	 * @param newDebt - The new Debt object to be persisted
	 * @param debtId - The id of the old Debt
	 * @param user - The User who owns the old Debt
	 * @return The persisted Debt object
	 * @author tylertanner
	 */
	Debt updateDebt(Debt newDebt, int debtId, User user);

}
