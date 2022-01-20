package com.mon.mymovies

/*

override fun onCreate(savedInstanceState: Bundle?) {  // carga la activity
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater) //ActivityMainBinding llama al activity_main.xml
    setContentView(binding.root)  // generea la estructura de la pantalla
}

override fun onStart() {    //activity pasa a primer plano
    super.onStart()
}

override fun onStop() {     //activity pasa a segunto plano
    super.onStop()
}

override fun onResume() {   //cuando hay algo por encima que la está bloqueando la activity
    super.onResume()
}

override fun onPause() {    //cuando hay algo por encima que la está bloqueando la activity
    super.onPause()
}

override fun onDestroy() {  //se destrulle la activity
    super.onDestroy()
}  */

/*


/*
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){  // El adapter debe cargar lñas vistas cuando sea necesario

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {   // crea una nueva vista
        carga de vista con el LayoutInflater, en nuestro ejemplo lo haremos con el ViewBinding
           val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_movie_item, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {   // actualiza la vista
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {   // devuelve el número de elementos que revive el adapter
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {   //  basada en la posición podemos decirle cual es el tipo de la vista
        return super.getItemViewType(position)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}



 */