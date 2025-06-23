import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

const Productos = () => {
  const navigate = useNavigate();

  // Estado para manejar la búsqueda
  const [searchTerm, setSearchTerm] = useState("");

  // Estado para productos - ahora se carga desde localStorage si existe
  const [productos, setProductos] = useState(() => {
    const savedProducts = localStorage.getItem("productos");
    if (savedProducts) {
      return JSON.parse(savedProducts);
    }
    // Datos de ejemplo por defecto
    return [
      {
        id: 1,
        nombre: "PRODUCTO DE EJEMPLO 1",
        cantidad: 20,
        tallas: [35, 36, 37, 38, 39],
        precio: "120000",
        imagen: null,
      },
      {
        id: 2,
        nombre: "EJEMPLO 2",
        cantidad: 20,
        tallas: [40, 41, 42, 43],
        precio: "150000",
        imagen: null,
      },
      {
        id: 3,
        nombre: "PRODUCTO DE EJEMPLO 3",
        cantidad: 20,
        tallas: [35, 36, 37, 38, 39, 40],
        precio: "180000",
        imagen: null,
      },
      {
        id: 4,
        nombre: "EJEMPLO 4",
        cantidad: 20,
        tallas: [41, 42, 43, 44, 45],
        precio: "200000",
        imagen: null,
      },
      {
        id: 5,
        nombre: "PRODUCTO DE EJEMPLO 5",
        cantidad: 20,
        tallas: [35, 36, 37],
        precio: "110000",
        imagen: null,
      },
      {
        id: 6,
        nombre: "EJEMPLO 6",
        cantidad: 20,
        tallas: [38, 39, 40, 41],
        precio: "160000",
        imagen: null,
      },
      {
        id: 7,
        nombre: "PRODUCTO DE EJEMPLO 7",
        cantidad: 20,
        tallas: [42, 43, 44, 45],
        precio: "190000",
        imagen: null,
      },
    ];
  });

  // Efecto para guardar productos en localStorage cuando cambien
  useEffect(() => {
    localStorage.setItem("productos", JSON.stringify(productos));
  }, [productos]);

  // Efecto para escuchar cambios desde otros componentes
  useEffect(() => {
    const handleStorageChange = () => {
      const savedProducts = localStorage.getItem("productos");
      if (savedProducts) {
        setProductos(JSON.parse(savedProducts));
      }
    };

    window.addEventListener("storage", handleStorageChange);

    // También escuchar un evento personalizado para cambios internos
    const handleProductUpdate = (event) => {
      if (event.detail) {
        setProductos(event.detail);
      }
    };

    window.addEventListener("productUpdated", handleProductUpdate);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
      window.removeEventListener("productUpdated", handleProductUpdate);
    };
  }, []);

  const createProduct = (e) => {
    e.preventDefault();
    navigate("/CrearProducto");
  };

  const editProduct = (productId) => {
    console.log("Editar producto:", productId);
    // Navegar al componente de edición con el ID del producto
    navigate(`/EditarProducto/${productId}`);
  };

  const deleteProduct = (productId) => {
    if (window.confirm("¿Estás seguro de que deseas eliminar este producto?")) {
      const updatedProducts = productos.filter(
        (producto) => producto.id !== productId
      );
      setProductos(updatedProducts);
      console.log("Producto eliminado:", productId);
    }
  };

  // Filtrar productos basado en la búsqueda
  const productosFiltrados = productos.filter((producto) =>
    producto.nombre.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  return (
    <div className="w-full md:min-h-screen flex items-start justify-center px-2 py-5 gap-6">
      <div className="w-full max-w-5xl mx-auto flex flex-col gap-8">
        {/* Encabezado de Productos */}
        <div className="w-full flex md:flex-row min-h-[100px] md:min-h-[100px] items-center justify-center px-4 border-b-2 border-dashed">
          <div className="w-1/2 flex items-start justify-start">
            <h2 className="text-4xl font-bold">LISTA DE PRODUCTOS</h2>
          </div>
          <div className="w-1/2 flex items-end justify-end">
            <button
              className="bg-[#FF7700] cursor-pointer text-xl font-bold text-white p-4 w-[250px] h-[70px] flex items-center justify-center rounded-2xl hover:bg-[#e06600] transition-colors"
              onClick={createProduct}
            >
              AÑADIR NUEVO PRODUCTO
            </button>
          </div>
        </div>
        {/* Encabezado de Productos End */}

        {/* Barra de Busqueda */}
        <div className="flex w-full md:flex-row items-center justify-center">
          <input
            type="text"
            className="w-full h-[50px] p-4 bg-white rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-[#FF7700] focus:border-transparent"
            placeholder="Buscar producto"
            value={searchTerm}
            onChange={handleSearchChange}
          />
        </div>
        {/* Barra de Busqueda End */}

        {/* Lista de Productos */}
        <div className="w-full flex flex-col gap-4">
          {productosFiltrados.length > 0 ? (
            productosFiltrados.map((producto) => (
              <div
                key={producto.id}
                className="w-full bg-[#AFCBD3] rounded-xl shadow-md p-4 flex items-center justify-between"
              >
                {/* Lado izquierdo - Icono y información del producto */}
                <div className="flex items-center gap-4">
                  {/* Icono del producto o imagen */}
                  <div className="w-12 h-12 bg-gray-700 rounded-lg flex items-center justify-center overflow-hidden">
                    {producto.imagen ? (
                      <img
                        src={producto.imagen}
                        alt={producto.nombre}
                        className="w-full h-full object-cover"
                      />
                    ) : (
                      <svg
                        className="w-8 h-8 text-white"
                        fill="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path d="M19 7h-3V6a4 4 0 0 0-8 0v1H5a1 1 0 0 0-1 1v11a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3V8a1 1 0 0 0-1-1zM10 6a2 2 0 0 1 4 0v1h-4V6zm8 13a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V9h2v1a1 1 0 0 0 2 0V9h4v1a1 1 0 0 0 2 0V9h2v10z" />
                      </svg>
                    )}
                  </div>

                  {/* Información del producto */}
                  <div>
                    <h3 className="text-lg font-bold text-gray-800">
                      {producto.nombre}
                    </h3>
                    <p className="text-sm text-gray-700">
                      Cantidad: {producto.cantidad}
                    </p>
                    <p className="text-sm text-gray-700">
                      Precio: ${parseInt(producto.precio).toLocaleString()}
                    </p>
                    <p className="text-xs text-gray-600">
                      Tallas: {producto.tallas.join(", ")}
                    </p>
                  </div>
                </div>

                {/* Lado derecho - Botones de acción */}
                <div className="flex items-center gap-2">
                  {/* Botón Editar */}
                  <button
                    onClick={() => editProduct(producto.id)}
                    className="w-10 h-10 bg-[#FF7700] hover:bg-[#e06600] rounded-lg flex items-center justify-center transition-colors cursor-pointer"
                    title="Editar producto"
                  >
                    <svg
                      className="w-5 h-5 text-white"
                      fill="none"
                      stroke="currentColor"
                      viewBox="0 0 24 24"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth={2}
                        d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"
                      />
                    </svg>
                  </button>

                  {/* Botón Eliminar */}
                  <button
                    onClick={() => deleteProduct(producto.id)}
                    className="w-10 h-10 bg-[#FF7700] hover:bg-[#e06600] rounded-lg flex items-center justify-center transition-colors cursor-pointer"
                    title="Eliminar producto"
                  >
                    <svg
                      className="w-5 h-5 text-white"
                      fill="none"
                      stroke="currentColor"
                      viewBox="0 0 24 24"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth={2}
                        d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
                      />
                    </svg>
                  </button>
                </div>
              </div>
            ))
          ) : (
            <div className="w-full bg-[#AFCBD3] rounded-xl shadow-md p-8 text-center">
              <p className="text-lg text-gray-700">
                {searchTerm
                  ? "No se encontraron productos que coincidan con la búsqueda."
                  : "No hay productos disponibles."}
              </p>
              {!searchTerm && (
                <button
                  onClick={createProduct}
                  className="mt-4 bg-[#FF7700] hover:bg-[#e06600] text-white px-6 py-2 rounded-lg font-medium transition-colors cursor-pointer"
                >
                  Crear tu primer producto
                </button>
              )}
            </div>
          )}
        </div>
        {/* Lista de Productos End */}
      </div>
    </div>
  );
};

export default Productos;
