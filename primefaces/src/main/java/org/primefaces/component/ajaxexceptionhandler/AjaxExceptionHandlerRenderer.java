/*
 * The MIT License
 *
 * Copyright (c) 2009-2024 PrimeTek Informatics
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.component.ajaxexceptionhandler;

import org.primefaces.expression.SearchExpressionUtils;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class AjaxExceptionHandlerRenderer extends CoreRenderer {

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        AjaxExceptionHandler ajaxExceptionHandler = (AjaxExceptionHandler) component;

        encodeMarkup(context, ajaxExceptionHandler);
        encodeScript(context, ajaxExceptionHandler);
    }

    protected void encodeScript(FacesContext context, AjaxExceptionHandler ajaxExceptionHandler) throws IOException {
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("AjaxExceptionHandler", ajaxExceptionHandler);
        wb.attr("exceptionType", ajaxExceptionHandler.getType())
                .attr("update", SearchExpressionUtils.resolveClientIdsForClientSide(context, ajaxExceptionHandler, ajaxExceptionHandler.getUpdate()))
                .callback("onexception", "function(errorName, errorMessage)", ajaxExceptionHandler.getOnexception());

        wb.finish();
    }

    protected void encodeMarkup(FacesContext context, AjaxExceptionHandler ajaxExceptionHandler) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = ajaxExceptionHandler.getClientId(context);

        writer.startElement("div", null);
        writer.writeAttribute("id", clientId, null);

        writer.writeAttribute("style", "display:none", null);

        writer.endElement("div");
    }

}