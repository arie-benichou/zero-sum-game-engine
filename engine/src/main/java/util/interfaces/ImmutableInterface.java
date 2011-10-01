
package util.interfaces;

import util.annotations.Application;

public interface ImmutableInterface<T> {

    //static T from();

    @Application
    T apply();

}