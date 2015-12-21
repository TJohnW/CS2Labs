/*
 * Cloner.java 
 */
package utilities;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import Players.TXW6529.Piece;

abstract class ClonerCommand {
    final Object iClone( Object original ) { return iClone( original, true ); }
    abstract Object iClone( Object original, boolean deep );
}

/**
 * Some utilities to make duplicates of objects of specific types
 * 
 * @author James Heliotis
 */
public class Cloner {

    /**
     * The classes and primitive types that will be copied
     * through assignment
     */
    @SuppressWarnings("serial" )
    public static final HashSet< Class<?> > IMMUTABLES =
                                    new HashSet< Class<?> >() {{
                                        add( Byte.class );
                                        add( Short.class );
                                        add( Integer.class );
                                        add( Long.class );
                                        add( Character.class );
                                        add( Float.class );
                                        add( Double.class );
                                        add( Boolean.class );
                                        add( String.class );
                                    }};

    /**
     * The classes that will be copied through copying of their components.
     * Though not included here, primitive arrays are also copied this way.
     */
    public static final
        HashMap< Class<?>, ClonerCommand > SUPPORTED_CLASSES =
                                    new HashMap< Class<?>, ClonerCommand >();
    
    /** The ClonerCommand class for LinkedLists */
    private static class LinkedListCloner extends ClonerCommand {
        @SuppressWarnings("unchecked" )
        @Override
        public Object iClone( Object original, boolean deep ) {
            LinkedList< Object > result = new LinkedList< Object >();
            for ( Object element: (LinkedList<Object>)original ) {
                result.add( deep ? deepCopy( element ): element );
            }
            return result;
        }
    }
    static {
        SUPPORTED_CLASSES.put( LinkedList.class, new LinkedListCloner() );
    }
    
    /** The ClonerCommand class for ArrayLists */
    private static class ArrayListCloner extends ClonerCommand {
        @SuppressWarnings("unchecked" )
        @Override
        public Object iClone( Object original, boolean deep ) {
            ArrayList< Object > result = new ArrayList< Object >();
            for ( Object element: (ArrayList<Object>)original ) {
                result.add( deep ? deepCopy( element ) : element );
            }
            return result;
        }
    }
    static {
        SUPPORTED_CLASSES.put( ArrayList.class, new ArrayListCloner() );
    }
    
    /** The ClonerCommand class for Stacks */
    private static class StackCloner extends ClonerCommand {
        { SUPPORTED_CLASSES.put( Stack.class, this ); }
        @SuppressWarnings("unchecked" )
        @Override
        public Object iClone( Object original, boolean deep ) {
            Stack< Object > result = new Stack< Object >();
            for ( Object element: (Stack<Object>)original ) {
                result.add( deep ? deepCopy( element ) : element );
            }
            return result;
        }
    }
    static {
        SUPPORTED_CLASSES.put( Stack.class, new StackCloner() );
    }

    /** The ClonerCommand class for TreeMaps */
    private static class TreeMapCloner extends ClonerCommand {
        { SUPPORTED_CLASSES.put( TreeMap.class, this ); }
        @SuppressWarnings("unchecked" )
        @Override
        public Object iClone( Object original, boolean deep ) {
            TreeMap< Object, Object > result = new TreeMap< Object, Object >();
            TreeMap< Object, Object > originalMap =
                    (TreeMap< Object, Object >)original;
            for ( Object key: originalMap.keySet() ) {
                Object value = originalMap.get( key );
                result.put( key, deep ? deepCopy( value ) : value );
            }
            return result;
        }
    }
    static {
        SUPPORTED_CLASSES.put( TreeMap.class, new TreeMapCloner() );
    }

    /** The ClonerCommand class for HashMaps */
    private static class HashMapCloner extends ClonerCommand {
        { SUPPORTED_CLASSES.put( HashMap.class, this ); }
        @SuppressWarnings("unchecked" )
        @Override
        public Object iClone( Object original, boolean deep ) {
            HashMap< Object, Object > result = new HashMap< Object, Object >();
            HashMap< Object, Object > originalMap =
                    (HashMap< Object, Object >)original;
            for ( Object key: originalMap.keySet() ) {
                Object value = originalMap.get( key );
                result.put( key, deep ? deepCopy( value ) : value );
            }
            return result;
        }
    }
    static {
        SUPPORTED_CLASSES.put( HashMap.class, new HashMapCloner() );
    }
    
    /** The ClonerCommand class for HashMaps */
    private static class GamePieceCloner extends ClonerCommand {
        { SUPPORTED_CLASSES.put( Piece.class, this ); }
        @SuppressWarnings("unchecked" )
        @Override
        public Object iClone( Object original, boolean deep ) {
            Piece originalPiece = (Piece) original;
            Piece result = new Piece(originalPiece.player_id, originalPiece.piece_size);
            return result;
        }
    }
    static {
        SUPPORTED_CLASSES.put( Piece.class, new GamePieceCloner() );
    }

    /**
     * Make a shallow copy of the object provided. That is, make a copy
     * at the very top level but use assignment semantics for the object's
     * components.
     * @param original the object to be copied
     * @return a new object containing all of the elements of the original
     * @exception IllegalArgumentException if the Object's type
     *     is not listed in IMMUTABLES or SUPPORTED CLASSES and
     *     is not an array
     */
    public static Object shallowCopy( Object original ) {
        Class<?> objectClass = original.getClass();
        if ( IMMUTABLES.contains( objectClass ) ) {
            return original;
        }
        else if ( objectClass.isArray() ) {
            Class<?> eltType = objectClass.getComponentType();
            int length = Array.getLength( original );
            Object copy = Array.newInstance( eltType, length );
            for ( int i = 0; i < length; ++i ) {
                Array.set( copy, i, Array.get( original, i ) );
            }
            return copy;
        }
        else if ( SUPPORTED_CLASSES.containsKey( objectClass ) ) {
            return SUPPORTED_CLASSES.get( objectClass )
                                      .iClone( original, false );
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Make a deep copy of the object provided. That is, the copying is done
     * recursively down to the basic types.
     * @see #IMMUTABLES
     * @param original the object to be copied
     * @return a precise copy of all of the elements of the object
     * @exception IllegalArgumentException if the Object's type
     *     is not listed in IMMUTABLES or SUPPORTED CLASSES and
     *     is not an array
     */
    public static Object deepCopy( Object original ) {
        Class<?> objectClass = original.getClass();
        if ( IMMUTABLES.contains( objectClass ) ) {
            return original;
        }
        else if ( objectClass.isArray() ) {
            Class<?> eltType = objectClass.getComponentType();
            int length = Array.getLength( original );
            Object copy = Array.newInstance( eltType, length );
            for ( int i = 0; i < length; ++i ) {
                Array.set( copy, i, deepCopy( Array.get( original, i ) ) );
            }
            return copy;
        }
        else if ( SUPPORTED_CLASSES.containsKey( objectClass ) ) {
            return SUPPORTED_CLASSES.get( objectClass ).iClone( original );
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}

/*
 * $Id: Cloner.java,v 1.1 2014/04/14 16:02:58 txw6529 Exp $
 *
 * $Log: Cloner.java,v $
 * Revision 1.1  2014/04/14 16:02:58  txw6529
 * Gobblet Solo Strat
 *
 *
 */