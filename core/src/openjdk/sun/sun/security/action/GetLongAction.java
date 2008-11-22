/*
 * Copyright 1998-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package sun.security.action;

/**
 * A convenience class for retrieving the <code>Long</code> value of a system
 * property as a privileged action.
 *
 * <p>An instance of this class can be used as the argument of
 * <code>AccessController.doPrivileged</code>.
 *
 * <p>The following code retrieves the <code>Long</code> value of the system
 * property named <code>"prop"</code> as a privileged action. Since it does
 * not pass a default value to be used in case the property
 * <code>"prop"</code> is not defined, it has to check the result for
 * <code>null</code>: <p>
 *
 * <pre>
 * Long tmp = java.security.AccessController.doPrivileged
 *     (new sun.security.action.GetLongAction("prop"));
 * long l;
 * if (tmp != null) {
 *     l = tmp.longValue();
 * }
 * </pre>
 *
 * <p>The following code retrieves the <code>Long</code> value of the system
 * property named <code>"prop"</code> as a privileged action, and also passes
 * a default value to be used in case the property <code>"prop"</code> is not
 * defined: <p>
 *
 * <pre>
 * long l = java.security.AccessController.doPrivileged
 *	(new GetLongAction("prop")).longValue();
 * </pre>
 *
 * @author Roland Schemers
 * @see java.security.PrivilegedAction
 * @see java.security.AccessController
 * @since 1.2
 */

public class GetLongAction implements java.security.PrivilegedAction<Long> {
    private String theProp;
    private long defaultVal;
    private boolean defaultSet = false;

    /**
     * Constructor that takes the name of the system property whose
     * <code>Long</code> value needs to be determined.
     *
     * @param theProp the name of the system property.
     */
    public GetLongAction(String theProp) {
	this.theProp = theProp;
    }

    /**
     * Constructor that takes the name of the system property and the default
     * value of that property.
     *
     * @param theProp the name of the system property.
     * @param defaulVal the default value.
     */
    public GetLongAction(String theProp, long defaultVal) {
        this.theProp = theProp;
        this.defaultVal = defaultVal;
	this.defaultSet = true;
    }

    /**
     * Determines the <code>Long</code> value of the system property whose
     * name was specified in the constructor.
     *
     * <p>If there is no property of the specified name, or if the property
     * does not have the correct numeric format, then a <code>Long</code>
     * object representing the default value that was specified in the
     * constructor is returned, or <code>null</code> if no default value was
     * specified.
     *
     * @return the <code>Long</code> value of the property.
     */
    public Long run() {
        Long value = Long.getLong(theProp);
        if ((value == null) && defaultSet)
	    return new Long(defaultVal);
	return value;
    }
}
