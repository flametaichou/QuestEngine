package ru.flametaichou.quest.core.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.flametaichou.quest.core.domain.Quest;

@Repository
public class QuestDaoImpl extends HibernateDaoSupport implements QuestDao {

    @Resource
    public void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveOrUpdate(Quest quest) {
        getHibernateTemplate().saveOrUpdate(quest);
    }

    @Override
    public Quest findById(long id) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Quest> quests = new ArrayList<Quest>();
            quests = sessionFactory.getCurrentSession()
                    .createQuery("from Quest q where q.id = :id")
                    .setLong( "id", id )
                    .list();

            if (quests.size() > 0) {
                return quests.get(0);
            } else {
                return null;
            }
        });
    }

    @Override
    public Quest findByUniqueCode(String uniqueCode) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Quest> quests = new ArrayList<Quest>();
            quests = sessionFactory.getCurrentSession()
                    .createQuery("from Quest q where q.uniqueCode = :uniqueCode")
                    .setString( "uniqueCode", uniqueCode )
                    .list();

            if (quests.size() > 0) {
                return quests.get(0);
            } else {
                return null;
            }
        });
    }

    @Override
    public void delete(Quest quest) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<Quest>() {
            @Override
            public Quest doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().delete(quest);
                return null;
            }
        });
    }

    @Override
    public List<Quest> listQuests() {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Quest> quests = new ArrayList<Quest>();

            quests = sessionFactory.getCurrentSession()
                    .createQuery("from Quest")
                    .list();

            return quests;
        });
    }
}
