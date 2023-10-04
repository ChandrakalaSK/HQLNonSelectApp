package in.ineuron.Test;

import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.Model.Product;
import in.ineuron.Util.HibernateUtil;

public class UpdateApp {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		Session session = null;
		
		Transaction transaction = null;
		
		boolean flag = false;
		
		int count = 0;
		
		try
		{
			session = HibernateUtil.getSession();
			transaction=session.beginTransaction();
			//Prepare query object to hold HQL
			Query query=session.createQuery("Update Product set qty=qty+:newQty where pname like :initialLetter");
			
			//Set the Parameter values
			query.setParameter("newQty", 5);
			query.setParameter("initialLetter", "f%");
			//Execute the query
		
			count=query.executeUpdate();
			
			flag = true;
			
		}
		catch (HibernateException he) 
		{
			
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag)
			{
				transaction.commit();
				System.out.println("No of rows affected "+count);
			}
			else
			{
				transaction.rollback();
				System.out.println("No of rows affected "+count);
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
			//Process the result
			
		}

	}

}
