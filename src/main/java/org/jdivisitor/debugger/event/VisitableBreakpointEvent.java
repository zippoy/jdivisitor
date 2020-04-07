/*
 * JDIVisitor
 * Copyright (C) 2014  Adrian Herrera
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.jdivisitor.debugger.event;

import com.sun.jdi.event.BreakpointEvent;
import lombok.AllArgsConstructor;
import org.jdivisitor.debugger.event.visitor.EventVisitor;
import org.jdivisitor.debugger.event.visitor.Visitable;

/**
 * Visitable breakpoint event.
 *
 * @author Adrian Herrera
 * @see BreakpointEvent
 */
@AllArgsConstructor
public class VisitableBreakpointEvent implements Visitable {

    private final BreakpointEvent event;

    @Override
    public void accept(EventVisitor visitor) {
        visitor.visit(this.event);
    }
}
