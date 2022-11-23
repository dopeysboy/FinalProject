package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;

public interface DebtLenderService {
	
	/**
	 * Will return all persisted DebtLenders
	 * @return List of all DebtLenders
	 * @author tylertanner
	 */
	List<DebtLender> findAll();
	
	/**
	 * Will return all DebtLenders whose name contains the given keyword
	 * @param keyword - The keyword to search for
	 * @return the List of all DebtLenders that match
	 * @author tylertanner
	 */
	List<DebtLender> findByKeyword(String keyword);
	
	/**
	 * Will return the persisted DebtLender that has the given id
	 * @param id - The id to search for
	 * @return DebtLender that matches the id or null if none is found
	 * @author tylertanner
	 */
	DebtLender findById(int id);
	
	/**
	 * If the passed User has a role of "admin", will persist the passed DebtLender
	 * @param debtLender - the DebtLender to persist
	 * @param user - The User attempting to persist a DebtLender
	 * @return the persisted DebtLender or null if the request failed
	 * @author tylertanner
	 */
	DebtLender create(DebtLender debtLender, User user);
	
	/**
	 * If the passed User has a role of "admin", will update an existing
	 * DebtLender with the passed debtLender
	 * @param debtLender - the updated DebtLender
	 * @param id - the existing DebtLender
	 * @param user - the User attempting to update a DebtLender
	 * @return the updated DebtLender or null if the request failed
	 * @author tylertanner
	 */
	DebtLender update(DebtLender debtLender, int id, User user);
	
	/**
	 * If the passed User has a role of "admin", will delete an existing
	 * DebtLender
	 * @param debtLender - the DebtLender to be deleted
	 * @param user - the User attempting to delete the DebtLender
	 * @return a boolean of whether the operation was successful or not
	 * @author tylertanner
	 */
	boolean destroy(DebtLender debtLender, User user);
	
	/**
	 * Will add a Debt to a DebtLender's list of Debts if the passed User
	 * owns the Debt
	 * @param dl - the DebtLender to edit
	 * @param debt - the Debt to add
	 * @param user - the User trying to conduct the action
	 * @return the updated DebtLender
	 * @author tylertanner
	 */
	DebtLender addDebtToDebtLender(DebtLender dl, Debt debt, User user);
	
	/**
	 * Will remove a Debt from a DebtLender's list of Debts if the passed User
	 * owns the Debt
	 * @param dl - the DebtLender to edit
	 * @param debt - the Debt to remove
	 * @param user - the User trying to conduct the action
	 * @return the updated DebtLender
	 * @author tylertanner
	 */
	DebtLender removeDebtFromDebtLender(DebtLender dl, Debt debt, User user);
}
