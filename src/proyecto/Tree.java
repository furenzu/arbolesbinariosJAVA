package proyecto;
public class Tree {
   /* 
   No esta soportado si se genera un índice i mayor al tamaño del 
   arreglo o si se llena éste.
   Probar con maxSize = 100 insertando 10 o 12 nodos.
   */
   private Node[] treeArray;
   private int maxSize;
   private int currentSize;
   private int i = 0;		 // i es el índice del arreglo en todos los métodos
   public Tree(int maxSize) {
      this.maxSize = maxSize;
      currentSize = 0;
      treeArray = new Node[maxSize];
   }
   public Node find(int key) {
      i = 0;      // empezar por el raíz
      while (treeArray[i] != null) {
         if (treeArray[i].getKey() == key) {
            return treeArray[i];
         }
         if (treeArray[i].getKey() > key) {
            i = 2 * i + 1;
         } else {
            i = 2 * i + 2;
         }
      }
      return null;		// no se encontró
   }
   public void insert(int key, double dData) {
      Node nuevo = new Node(key, dData);
      if (treeArray[0] == null) {     // árbol vacío
         treeArray[0] = nuevo;
         return;
      }
      i = 0;
      while (true) {
         if (treeArray[i].getKey() == key) {
            System.out.println("Llave duplicada, no se puede insertar");
            return;
         }
         if (treeArray[i].getKey() > key) {
            i = 2 * i + 1;        // va al hijo izquierdo
            if (treeArray[i] == null) {
               treeArray[i] = nuevo;
               return;
            }
         } else {
            i = 2 * i + 2;        // va al hijo derecho
            if (treeArray[i] == null) {
               treeArray[i] = nuevo;
               return;
            }
         }
      }
   }
   public Node minimum() {
      i = 0;
      int iMin = 0;
      while (treeArray[i] != null) {
         iMin = i;
         i = 2 * i + 1;
      }
      return treeArray[iMin];
   }
   public Node maximum() {
      i = 0;
      int iMax = 0;
      while (treeArray[i] != null) {
         System.out.println("n = " + treeArray[i].getKey());
         iMax = i;
         i = 2 * i + 2;
      }
      return treeArray[iMax];
   }
   public boolean delete(int key) {
      if (find(key) == null) {
         System.out.println("No existe el nodo a borrar");
         return false;
      }
      // i es el índice del nodo encontrado
      int hijoIzq = 2 * i + 1;
      int hijoDer = 2 * i + 2;
      boolean borrado = false;
      // i es el índice del nodo encontrado dejado por el método find
      if (treeArray[hijoIzq] == null && treeArray[hijoDer] == null) {
         // caso 1, el nodo a borrar no tiene hijos
         treeArray[i] = null;
         borrado =  true;
      } else {                                  // casos 2 y 3
         if (treeArray[hijoIzq] != null && treeArray[hijoDer] == null) {
            // Caso 2a, el nodo a borrar sólo tiene hijo izquierdo
            System.out.println("Caso 2a, el nodo a borrar sólo tiene hijo izquierdo... TBI");
            borrado = false;
         } else {
            if (treeArray[hijoIzq] == null && treeArray[hijoDer] != null) {
               // Caso 2b, el nodo a borrar sólo tiene hijo derecho
               System.out.println("Caso 2b, el nodo a borrar sólo tiene hijo derecho ... TBI");
               borrado = false;
            } else {
               if (treeArray[hijoIzq] != null && treeArray[hijoDer] != null) {
                  // Caso 3, el nodo a borrar tiene los dos hijos
                  // NO FUNCIONA BIEN cuando hay subárboles del borrado
                  int sucesor = getIndiceSucesor(i);       // i es el índice del nodo a borrar
                  treeArray[i] = treeArray[sucesor];       // substituye nodo encontrado para borrar por su sucesor  
                  treeArray[sucesor] = null;               // borrar el que era el sucesor
                  borrado = true;
               }
            }
         }
      }
      return borrado;
   }
   private int getIndiceSucesor(int i) {
      int sucesor = 0;                                       // no necesario igualarlo a 0 pero hay que inicializarlo  
      int indHijoDer = 2 * i + 2;                            // índice del hijo derecho del nodo original
      int indHijoIzq = 2 * indHijoDer + 1;                   // hijo izquierdo del hijo derecho
      if (treeArray[indHijoIzq] == null) {
         sucesor = indHijoDer;                               // este nodo es el sucesor
      } else {
         int indHijoIzqHijoIzq = 2 * indHijoIzq + 1;         // hijo izquierdo del hijo izquierdo del hijo derecho
         while (treeArray[indHijoIzqHijoIzq] != null) {
            sucesor = indHijoIzqHijoIzq;                     // el último hijo izquierdo no nulo es el sucesor.
            indHijoIzqHijoIzq = 2 * indHijoIzqHijoIzq + 1;   // seguir viajando a hijos izquierdos  
         }
      }
      return sucesor;
   }
   public void displayTree() {
      System.out.print("treeArray: ");
      for (int j = 0; j < maxSize; j++) {
         if (treeArray[j] != null) {
            System.out.print(treeArray[j].getKey() + " ");
         } else {
            System.out.print(" - ");
         }
      }
      System.out.println();
   }
}
