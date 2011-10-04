
package configurations;

/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import context.Context;
import context.ContextInterface;
import context.bootstrap.Bootstrap;
import context.bootstrap.BootstrapInterface;

class Connect4 {

    public static void main(final String[] args) {

        //final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("Connect4.xml"));
        final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("Context2.xml"));

        final ContextInterface context = (Context) factory.getBean("context");

        @SuppressWarnings("unchecked")
        final Map<Object, Object> symbols = (Map<Object, Object>) factory.getBean("symbols");

        final BootstrapInterface bootStrap = Bootstrap.from(context, symbols);

        bootStrap.run();

    }

}