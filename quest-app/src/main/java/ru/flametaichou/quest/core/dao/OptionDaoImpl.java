package ru.flametaichou.quest.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.flametaichou.quest.core.domain.Option;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OptionDaoImpl extends HibernateDaoSupport implements OptionDao {

    @Resource
    public void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveOrUpdate(Option option) {
        getHibernateTemplate().saveOrUpdate(option);
    }

    @Override
    public Option findById(long id) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Option> options = new ArrayList<Option>();
            options = sessionFactory.getCurrentSession()
                    .createQuery("from Option option where option.id=?")
                    .setParameter(0, id)
                    .list();

            if (options.size() > 0) {
                return options.get(0);
            } else {
                return null;
            }
        });
    }

    @Override
    public void delete(Option option) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<Option>() {
            @Override
            public Option doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().delete(option);
                return null;
            }
        });
    }
}
