public class TaskList extends BaseContainer{
    
    public int TimeSpent;
    
    public TaskList(){
        super();
    }
    
    public void addTask(Task Tarea){
        Node NodeTarea = new Node(Tarea);
        //si vacia
        if (first == null){
            first = last = NodeTarea;
            return;
        }
        //si no
        Node puntero = first;
        while(puntero.next != null){
            puntero = puntero.next;
        }
        puntero.next = NodeTarea;
        last = NodeTarea;
    }
    
    public Task getTask(int position){
        if (first == null){
            return null;
        }
        Node puntero = first;
        int contador_posicion = 0;
        while (contador_posicion < position){
            puntero = puntero.next;
            contador_posicion ++; 
        }
        if (puntero == null){
            return null;
        }
        return puntero.value;
    }
    
    public Task taskCompleted(int position){
        if (first == null){
            return null;
        }
        
        if (position == 0){
            Task Tarea = first.value;
            first = first.next;
            if (first == null){
                last = null;
            }
            TimeSpent += Tarea.duration;
            return Tarea;
        }
        Node puntero = first;
        int contador_posicion = 0;
        while (puntero.next != null && contador_posicion < position - 1){
            puntero = puntero.next;
            contador_posicion ++;
        }
        if (puntero.next == null){
            return null;
        }
        Task Tarea = puntero.next.value;
        puntero.next = puntero.next.next;
        TimeSpent += puntero.value.duration;
        return Tarea;
    }
    
    //total = bucle con contador
    public int getTimeLeft(){
        int tiempo_total_left = 0;
        Node puntero = first;
        if (first == null){
            return 0;
        }
        while(puntero != null){
            tiempo_total_left += puntero.value.duration;
            puntero = puntero.next;
        }
        return tiempo_total_left;
        
    }
    
    //recorrer y si completada suma a contador
    public int getTimeSpent(){
        return TimeSpent;
    }
}
