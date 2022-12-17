package tech.tryu.iterator;

import tech.tryu.iterator.interfaces.Iterator;
import tech.tryu.iterator.entity.Profile;

/**
 * 社交查询
 *
 * @author YU
 * @date 2022-05-06 19:01
 */
public class SocialSpammer {

    public void send(Iterator iterator, String message) {
        while (iterator.hasMore()){
            Profile profile = iterator.getNext();
            System.out.println(profile.getEmail() + message);
        }
    }
}
