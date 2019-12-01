package com.example.chh;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HelloWorld2 {  
    public static void main(String args[]) {  
        Shell shell = new Shell();
        shell.setText("Draw2d Hello World");
        shell.setSize(300, 300);
        shell.open();

        // create content 4 shell.
        createContent4Shell(shell);




        while (!shell.isDisposed ()) {
            if (!Display.getDefault().readAndDispatch ())
                Display.getDefault().sleep ();
        }
    }

  
    private static void createContent4Shell(Shell shell) {  
        Panel rootFigure = new Panel();  
        rootFigure.setLayoutManager(new XYLayout());  
  
        IFigure figure1 = new Ellipse();  
        Ellipse figure2 = new Ellipse();  
        Ellipse figure3 = new Ellipse();

        // --------------------------------------------------------  
        // add connection  
        PolylineConnection connection = new PolylineConnection();  
        PolylineConnection connection2 = new PolylineConnection();
        connection.setSourceAnchor(new ChopboxAnchor(figure1));
        connection.setTargetAnchor(new EllipseAnchor(figure2));
        connection2.setSourceAnchor(new ChopboxAnchor(figure1));
        connection2.setTargetAnchor(new EllipseAnchor(figure3));

        // --------------------------------------------------------  
        // add Decoration to Connection  
        connection.setSourceDecoration(new PolygonDecoration());  
        connection2.setSourceDecoration(new PolygonDecoration());

        // --------------------------------------------------------  
        // add Labels to Connection  
        ConnectionEndpointLocator Locator1 = new ConnectionEndpointLocator(connection, true);  
        Locator1.setVDistance(30);

//        connection.add(new Label("1..*"), Locator1);
          
        ConnectionEndpointLocator Locator2 = new ConnectionEndpointLocator(connection, false);  
        Locator2.setVDistance(15);  
//        connection.add(new Label("22..*"), Locator2);
  
        ConnectionEndpointLocator Locator3 = new ConnectionEndpointLocator(connection,true);  
        Locator3.setUDistance(80);  
        Locator3.setVDistance(-20);  
//        connection.add(new Label("3333..*"),Locator3);


        ConnectionEndpointLocator Locator1_2 = new ConnectionEndpointLocator(connection2, true);
        Locator1_2.setVDistance(30);

//        connection2.add(new Label("..*"), Locator1_2);

//        ConnectionEndpointLocator Locator2_2 = new ConnectionEndpointLocator(connection2, false);
//        Locator2_2.setVDistance(15);
//        connection2.add(new Label("2..*"), Locator2_2);
//
//        ConnectionEndpointLocator Locator3_2 = new ConnectionEndpointLocator(connection2,true);
//        Locator3_2.setUDistance(80);
//        Locator3_2.setVDistance(-20);
//        connection2.add(new Label("3..*"),Locator3_2);

        // --------------------------------------------------------  
        // add Router to Connection
        ManhattanConnectionRouter manhattanConnectionRouter = new ManhattanConnectionRouter();
        ShortestPathConnectionRouter shortestPathConnectionRouter = new ShortestPathConnectionRouter(figure1);
        BendpointConnectionRouter bendpointConnectionRouter = new BendpointConnectionRouter();
//        manhattanConnectionRouter.invalidate(connection);
//        manhattanConnectionRouter.route(connection);
        connection.setConnectionRouter(manhattanConnectionRouter);
        connection2.setConnectionRouter(shortestPathConnectionRouter);

        rootFigure.add(figure1,new Rectangle(10,10,60,30));
        rootFigure.add(figure2,new Rectangle(170,40,90,90));
        rootFigure.add(figure3,new Rectangle(60,90,90,90));
        rootFigure.add(connection);
        rootFigure.add(connection2);

        LightweightSystem lws = new LightweightSystem(shell);
        lws.setContents(rootFigure);


    }  
}