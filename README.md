# areUin Android

**Responsable: Marlon Viana**

## Arquitectura del proyecto
El proyecto sigue una arquitectura MVVM, utiliza databingin  en la mayoría de los casos
para la relación (vista-maquetado vista-Logica), también se utiliza
las clases y métodos nativas para la implementación del viewModel.

Se aconseja el menor uso posible de librerías de terceros.

## Consumo de Api
Se utiliza Retrofit para el consumo de apis Rest, toda la lógica pertinente al consumo del api y
al procesado de las respuestas se encuentra ubicada en la carpeta: App -> Java -> api

## Inyeccion de dependencias
Se utiliza Dagger 2 para la inyección de dependencias.

## Gerarquia
- **activity:** contiene todas las clases AppCompatActivity.
- **adapters:** contiene todas las clases RecyclerView.Adapter y Decorator.
- **api:** contiene todas las clases necesarias para implementar el consumo de Api y el procesado de sus respuestas.
- **customView:** contiene todas las clases de vistas customizadas.
- **dagger** contiene todas las clases necesarias para implementar la inyección de dependencias.
- **dialog:** contiene todas las clases BottomSheetDialogFragment y DialogFragment.
- **event:** contiene todas las clases de tipo interface.
- **fragment:** contiene todas las clases Fragment.
- **models:** contiene todos los modelos de datos.
- **room:** contiene todas las clases necesarias para implementar room database.
- **service:** contiene todos los servicios que se ejecutaran en segundo grado.
- **util:** contiene todas las clases de utilidades.
- **viewModel:** contiene todas las clases ViewModel

## Prefijos
- **Activity:** activitys
- **Adapter:** clases adaptadores
- **Decorator:** clases decoradoras para los adaptadores
- **Api:** las interfaces de los request
- **Request:** clases que contiene los metodos que consumen api
- **BottomSheet:** BottomSheet
- **Dialog:** Dialogos
- **Event :** interface de eventos
- **Fragment:** fragmentos
- **anim :** animaciones de layout
- **ic_ :** icono en formato svg
- **img_ :** imagienes
- **sp_ :** xml shape y selector

## Sufijo

- **ViewModel:** clases ViewModel

