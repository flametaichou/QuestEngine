package ru.flametaichou.quest.core.dao;

import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.flametaichou.quest.core.domain.AccountRole;

@Repository
public class AccountRoleDaoImpl extends HibernateDaoSupport implements AccountRoleDao {

    @Resource
    public void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveOrUpdate(AccountRole accountRole) {
        getHibernateTemplate().saveOrUpdate(accountRole);
    }


    @Override
    public void delete(AccountRole accountRole) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<AccountRole>() {
            @Override
            public AccountRole doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().delete(accountRole);
                return null;
            }
        });
    }

    @Override
    public void update(AccountRole accountRole) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<AccountRole>() {
            @Override
            public AccountRole doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().update(accountRole);
                return null;
            }
        });
    }
}
