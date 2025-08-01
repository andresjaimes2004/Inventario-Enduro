// import { useState, useEffect } from "react";

// const AnadirMovimiento = () => {
//   // Estado para productos disponibles
//   const [productos, setProductos] = useState([
//     {
//       id: 1,
//       nombre: "PRODUCTO DE EJEMPLO 1",
//       cantidad: 20,
//       tallas: [35, 36, 37, 38, 39],
//       precio: "120000",
//     },
//     {
//       id: 2,
//       nombre: "EJEMPLO 2",
//       cantidad: 20,
//       tallas: [40, 41, 42, 43],
//       precio: "150000",
//     },
//     {
//       id: 3,
//       nombre: "PRODUCTO DE EJEMPLO 3",
//       cantidad: 20,
//       tallas: [35, 36, 37, 38, 39, 40],
//       precio: "180000",
//     },
//     {
//       id: 4,
//       nombre: "EJEMPLO 4",
//       cantidad: 20,
//       tallas: [41, 42, 43, 44, 45],
//       precio: "200000",
//     },
//   ]);

//   // Estados del formulario
//   const [selectedProduct, setSelectedProduct] = useState(null);
//   const [selectedReason, setSelectedReason] = useState("");
//   const [showProductDropdown, setShowProductDropdown] = useState(false);
//   const [showReasonDropdown, setShowReasonDropdown] = useState(false);
//   const [tallasCantidades, setTallasCantidades] = useState({});
//   const [showWarning, setShowWarning] = useState(true);

//   // Opciones de razón
//   const razonesDisponibles = [
//     "VENTA DE PRODUCTO",
//     "INGRESO DE INVENTARIO",
//     "DEVOLUCIÓN",
//     "AJUSTE DE INVENTARIO",
//     "PRODUCTO DAÑADO",
//     "REGALO/PROMOCIÓN",
//   ];

//   // Efecto para mostrar/ocultar sección de tallas
//   useEffect(() => {
//     if (selectedProduct && selectedReason) {
//       setShowWarning(false);
//       // Inicializar cantidades de tallas
//       const initialTallas = {};
//       selectedProduct.tallas.forEach((talla) => {
//         initialTallas[talla] = 0;
//       });
//       setTallasCantidades(initialTallas);
//     } else {
//       setShowWarning(true);
//     }
//   }, [selectedProduct, selectedReason]);

//   const handleProductSelect = (product) => {
//     setSelectedProduct(product);
//     setShowProductDropdown(false);
//   };

//   const handleReasonSelect = (reason) => {
//     setSelectedReason(reason);
//     setShowReasonDropdown(false);
//   };

//   const handleTallaChange = (talla, cantidad) => {
//     setTallasCantidades((prev) => ({
//       ...prev,
//       [talla]: parseInt(cantidad) || 0,
//     }));
//   };

//   const handleSubmit = () => {
//     if (!selectedProduct || !selectedReason) {
//       alert("Por favor selecciona un producto y una razón");
//       return;
//     }

//     const totalCantidad = Object.values(tallasCantidades).reduce(
//       (sum, cant) => sum + cant,
//       0
//     );

//     if (totalCantidad === 0) {
//       alert("Por favor ingresa al menos una cantidad para las tallas");
//       return;
//     }

//     // Aquí se agregaría la lógica para guardar el movimiento
//     const nuevoMovimiento = {
//       id: Date.now(),
//       titulo: selectedProduct.nombre,
//       fecha: new Date().toLocaleString("es-ES", {
//         hour: "2-digit",
//         minute: "2-digit",
//         hour12: true,
//         day: "2-digit",
//         month: "2-digit",
//         year: "numeric",
//       }),
//       razon: selectedReason,
//       habian: selectedProduct.cantidad,
//       hay:
//         selectedReason.includes("VENTA") || selectedReason.includes("DAÑADO")
//           ? selectedProduct.cantidad - totalCantidad
//           : selectedProduct.cantidad + totalCantidad,
//       tipo:
//         selectedReason.includes("VENTA") || selectedReason.includes("DAÑADO")
//           ? "Salida"
//           : "Entrada",
//       productId: selectedProduct.id,
//       tallas: tallasCantidades,
//     };

//     console.log("Nuevo movimiento:", nuevoMovimiento);
//     alert("Movimiento creado exitosamente!");

//     // Resetear formulario
//     setSelectedProduct(null);
//     setSelectedReason("");
//     setTallasCantidades({});
//   };

//   return (
//     <div className="w-full min-h-screen flex items-center justify-center px-4 py-8 bg-gray-100">
//       <div className="w-full max-w-4xl bg-[#AFCBD3] rounded-3xl p-8 shadow-lg">
//         {/* Título */}
//         <h1 className="text-3xl font-bold text-black mb-8">
//           AÑADIR MOVIMIENTO
//         </h1>

//         {/* Selección de Producto y Razón */}
//         <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
//           {/* Dropdown Nombre */}
//           <div className="relative">
//             <label className="block text-lg font-semibold text-black mb-2">
//               Nombre
//             </label>
//             <div className="relative">
//               <button
//                 onClick={() => setShowProductDropdown(!showProductDropdown)}
//                 className="w-full bg-white rounded-full px-4 py-3 text-left flex items-center justify-between focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
//               >
//                 <span
//                   className={selectedProduct ? "text-black" : "text-gray-400"}
//                 >
//                   {selectedProduct
//                     ? selectedProduct.nombre
//                     : "Seleccionar producto"}
//                 </span>
//                 <svg
//                   className={`w-5 h-5 text-gray-400 transition-transform ${
//                     showProductDropdown ? "rotate-180" : ""
//                   }`}
//                   fill="none"
//                   stroke="currentColor"
//                   viewBox="0 0 24 24"
//                 >
//                   <path
//                     strokeLinecap="round"
//                     strokeLinejoin="round"
//                     strokeWidth={2}
//                     d="M19 9l-7 7-7-7"
//                   />
//                 </svg>
//               </button>

//               {showProductDropdown && (
//                 <div className="absolute top-full left-0 right-0 bg-white border border-gray-200 rounded-lg shadow-lg z-10 max-h-60 overflow-y-auto">
//                   {productos.map((product) => (
//                     <button
//                       key={product.id}
//                       onClick={() => handleProductSelect(product)}
//                       className="w-full text-left px-4 py-3 hover:bg-gray-100 border-b border-gray-100 last:border-b-0"
//                     >
//                       <div className="font-medium">{product.nombre}</div>
//                       <div className="text-sm text-gray-600">
//                         Cantidad: {product.cantidad} | Precio: $
//                         {parseInt(product.precio).toLocaleString()}
//                       </div>
//                     </button>
//                   ))}
//                 </div>
//               )}
//             </div>
//           </div>

//           {/* Dropdown Razón */}
//           <div className="relative">
//             <label className="block text-lg font-semibold text-black mb-2">
//               Razón
//             </label>
//             <div className="relative">
//               <button
//                 onClick={() => setShowReasonDropdown(!showReasonDropdown)}
//                 className="w-full bg-white rounded-full px-4 py-3 text-left flex items-center justify-between focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
//               >
//                 <span
//                   className={selectedReason ? "text-black" : "text-gray-400"}
//                 >
//                   {selectedReason || "Seleccionar razón"}
//                 </span>
//                 <svg
//                   className={`w-5 h-5 text-gray-400 transition-transform ${
//                     showReasonDropdown ? "rotate-180" : ""
//                   }`}
//                   fill="none"
//                   stroke="currentColor"
//                   viewBox="0 0 24 24"
//                 >
//                   <path
//                     strokeLinecap="round"
//                     strokeLinejoin="round"
//                     strokeWidth={2}
//                     d="M19 9l-7 7-7-7"
//                   />
//                 </svg>
//               </button>

//               {showReasonDropdown && (
//                 <div className="absolute top-full left-0 right-0 bg-white border border-gray-200 rounded-lg shadow-lg z-10">
//                   {razonesDisponibles.map((reason) => (
//                     <button
//                       key={reason}
//                       onClick={() => handleReasonSelect(reason)}
//                       className="w-full text-left px-4 py-3 hover:bg-gray-100 border-b border-gray-100 last:border-b-0"
//                     >
//                       {reason}
//                     </button>
//                   ))}
//                 </div>
//               )}
//             </div>
//           </div>
//         </div>

//         {/* Mensaje de advertencia o sección de tallas */}
//         {showWarning ? (
//           <div className="bg-gray-200 rounded-2xl p-12 text-center">
//             <div className="w-12 h-12 bg-red-500 rounded-full flex items-center justify-center mx-auto mb-4">
//               <span className="text-white font-bold text-xl">W</span>
//             </div>
//             <p className="text-lg font-semibold text-gray-800">
//               Selecciona un producto
//               <br />
//               para continuar
//             </p>
//           </div>
//         ) : (
//           <div className="bg-gray-200 rounded-2xl p-6">
//             <h3 className="text-xl font-bold text-black mb-6">Tallas</h3>

//             {/* Grid de tallas */}
//             <div className="grid grid-cols-2 md:grid-cols-5 gap-4 mb-8">
//               {selectedProduct?.tallas.map((talla) => (
//                 <div key={talla} className="flex items-center gap-2">
//                   <span className="font-semibold text-lg min-w-[40px]">
//                     {talla}:
//                   </span>
//                   <input
//                     type="number"
//                     min="0"
//                     value={tallasCantidades[talla] || 0}
//                     onChange={(e) => handleTallaChange(talla, e.target.value)}
//                     className="w-16 h-8 px-2 border border-gray-300 rounded text-center focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
//                   />
//                 </div>
//               ))}
//             </div>

//             {/* Botón Añadir Movimiento */}
//             <div className="flex justify-center">
//               <button
//                 onClick={handleSubmit}
//                 className="bg-[#FF7700] hover:bg-[#e06600] text-white font-bold py-4 px-8 rounded-full text-lg transition-colors cursor-pointer"
//               >
//                 AÑADIR MOVIMIENTO
//               </button>
//             </div>
//           </div>
//         )}
//       </div>
//     </div>
//   );
// };

// export default AnadirMovimiento;

import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const AnadirMovimiento = () => {
  // Estado para productos disponibles - ahora se carga desde localStorage
  const [productos, setProductos] = useState([]);

  // Estados del formulario
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [selectedReason, setSelectedReason] = useState("");
  const [showProductDropdown, setShowProductDropdown] = useState(false);
  const [showReasonDropdown, setShowReasonDropdown] = useState(false);
  const [tallasCantidades, setTallasCantidades] = useState({});
  const [showWarning, setShowWarning] = useState(true);

  // Opciones de razón
  const razonesDisponibles = [
    "VENTA DE PRODUCTO",
    "INGRESO DE INVENTARIO",
    "DEVOLUCIÓN",
    "AJUSTE DE INVENTARIO",
    "PRODUCTO DAÑADO",
    "REGALO/PROMOCIÓN",
  ];

  // Cargar productos desde localStorage al montar el componente
  useEffect(() => {
    const loadProductos = () => {
      const savedProducts = localStorage.getItem("productos");
      if (savedProducts) {
        const parsedProducts = JSON.parse(savedProducts);
        setProductos(parsedProducts);
      } else {
        // Si no hay productos guardados, usar datos de ejemplo
        const defaultProducts = [
          {
            id: 1,
            nombre: "PRODUCTO DE EJEMPLO 1",
            cantidad: 20,
            tallas: [35, 36, 37, 38, 39],
            precio: "120000",
          },
          {
            id: 2,
            nombre: "EJEMPLO 2",
            cantidad: 20,
            tallas: [40, 41, 42, 43],
            precio: "150000",
          },
          {
            id: 3,
            nombre: "PRODUCTO DE EJEMPLO 3",
            cantidad: 20,
            tallas: [35, 36, 37, 38, 39, 40],
            precio: "180000",
          },
          {
            id: 4,
            nombre: "EJEMPLO 4",
            cantidad: 20,
            tallas: [41, 42, 43, 44, 45],
            precio: "200000",
          },
        ];
        setProductos(defaultProducts);
      }
    };

    loadProductos();
  }, []);

  // Escuchar cambios en localStorage y eventos personalizados
  useEffect(() => {
    const handleStorageChange = () => {
      const savedProducts = localStorage.getItem("productos");
      if (savedProducts) {
        const parsedProducts = JSON.parse(savedProducts);
        setProductos(parsedProducts);

        // Si el producto seleccionado fue eliminado, resetear la selección
        if (selectedProduct) {
          const productExists = parsedProducts.find(
            (p) => p.id === selectedProduct.id
          );
          if (!productExists) {
            setSelectedProduct(null);
            setSelectedReason("");
            setTallasCantidades({});
          } else {
            // Actualizar el producto seleccionado con los datos más recientes
            setSelectedProduct(productExists);
          }
        }
      }
    };

    // Escuchar cambios en localStorage desde otras pestañas
    window.addEventListener("storage", handleStorageChange);

    // Escuchar eventos personalizados para cambios internos
    const handleProductUpdate = (event) => {
      if (event.detail) {
        setProductos(event.detail);

        // Si el producto seleccionado fue eliminado, resetear la selección
        if (selectedProduct) {
          const productExists = event.detail.find(
            (p) => p.id === selectedProduct.id
          );
          if (!productExists) {
            setSelectedProduct(null);
            setSelectedReason("");
            setTallasCantidades({});
          } else {
            // Actualizar el producto seleccionado con los datos más recientes
            setSelectedProduct(productExists);
          }
        }
      }
    };

    window.addEventListener("productosUpdated", handleProductUpdate);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
      window.removeEventListener("productosUpdated", handleProductUpdate);
    };
  }, [selectedProduct]);

  // Efecto para mostrar/ocultar sección de tallas
  useEffect(() => {
    if (selectedProduct && selectedReason) {
      setShowWarning(false);
      // Inicializar cantidades de tallas
      const initialTallas = {};
      selectedProduct.tallas.forEach((talla) => {
        initialTallas[talla] = 0;
      });
      setTallasCantidades(initialTallas);
    } else {
      setShowWarning(true);
    }
  }, [selectedProduct, selectedReason]);

  const handleProductSelect = (product) => {
    setSelectedProduct(product);
    setShowProductDropdown(false);
  };

  const handleReasonSelect = (reason) => {
    setSelectedReason(reason);
    setShowReasonDropdown(false);
  };

  const handleTallaChange = (talla, cantidad) => {
    setTallasCantidades((prev) => ({
      ...prev,
      [talla]: parseInt(cantidad) || 0,
    }));
  };

  const updateProductInventory = (productId, totalCantidad, isDeduction) => {
    const savedProducts = localStorage.getItem("productos");
    if (savedProducts) {
      const products = JSON.parse(savedProducts);
      const updatedProducts = products.map((product) => {
        if (product.id === productId) {
          return {
            ...product,
            cantidad: isDeduction
              ? product.cantidad - totalCantidad
              : product.cantidad + totalCantidad,
          };
        }
        return product;
      });

      localStorage.setItem("productos", JSON.stringify(updatedProducts));

      // Disparar evento para notificar a otros componentes
      window.dispatchEvent(
        new CustomEvent("productosUpdated", {
          detail: updatedProducts,
        })
      );

      setProductos(updatedProducts);
    }
  };

  const handleSubmit = () => {
    if (!selectedProduct || !selectedReason) {
      alert("Por favor selecciona un producto y una razón");
      return;
    }

    const totalCantidad = Object.values(tallasCantidades).reduce(
      (sum, cant) => sum + cant,
      0
    );

    if (totalCantidad === 0) {
      alert("Por favor ingresa al menos una cantidad para las tallas");
      return;
    }

    // Determinar si es una reducción o aumento de inventario
    const isDeduction =
      selectedReason.includes("VENTA") ||
      selectedReason.includes("DAÑADO") ||
      selectedReason.includes("REGALO");

    // Verificar si hay suficiente inventario para reducciones
    if (isDeduction && totalCantidad > selectedProduct.cantidad) {
      alert("No hay suficiente inventario disponible para esta operación");
      return;
    }

    // Actualizar el inventario del producto
    updateProductInventory(selectedProduct.id, totalCantidad, isDeduction);

    // Crear el movimiento
    const nuevoMovimiento = {
      id: Date.now(),
      titulo: selectedProduct.nombre,
      fecha: new Date().toLocaleString("es-ES", {
        hour: "2-digit",
        minute: "2-digit",
        hour12: true,
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
      }),
      razon: selectedReason,
      habian: selectedProduct.cantidad,
      hay: isDeduction
        ? selectedProduct.cantidad - totalCantidad
        : selectedProduct.cantidad + totalCantidad,
      tipo: isDeduction ? "Salida" : "Entrada",
      productId: selectedProduct.id,
      tallas: tallasCantidades,
    };

    // Guardar el movimiento en localStorage
    const savedMovements = localStorage.getItem("movimientos");
    const movements = savedMovements ? JSON.parse(savedMovements) : [];
    movements.push(nuevoMovimiento);
    localStorage.setItem("movimientos", JSON.stringify(movements));

    console.log("Nuevo movimiento:", nuevoMovimiento);
    alert("Movimiento creado exitosamente!");

    // Resetear formulario
    setSelectedProduct(null);
    setSelectedReason("");
    setTallasCantidades({});
  };
  const navigate = useNavigate(); // Hook para navegar

  const goBack = () => {
    // Función para volver atrás
    navigate("/Movimientos");
  };

  return (
    <div className="w-full min-h-screen flex items-center justify-center px-4 py-8 bg-gray-100">
      <div className="w-full max-w-4xl bg-[#AFCBD3] rounded-3xl p-8 shadow-lg">
        {/* Header con botón volver */}
        <div className="flex-shrink-0 px-4 pb-2">
          <button
            onClick={goBack}
            className="mb-2 text-orange-400 hover:text-orange-500 cursor-pointer font-medium flex items-center gap-2"
          >
            ← Volver a Movimientos
          </button>
          <h1 className="text-2xl font-bold text-gray-900">
            AÑADIR MOVIMIENTO
          </h1>
        </div>

        {/* Selección de Producto y Razón */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
          {/* Dropdown Nombre */}
          <div className="relative">
            <label className="block text-lg font-semibold text-black mb-2">
              Nombre
            </label>
            <div className="relative">
              <button
                onClick={() => setShowProductDropdown(!showProductDropdown)}
                className="w-full bg-white rounded-full px-4 py-3 text-left flex items-center justify-between focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
              >
                <span
                  className={selectedProduct ? "text-black" : "text-gray-400"}
                >
                  {selectedProduct
                    ? selectedProduct.nombre
                    : productos.length > 0
                    ? "Seleccionar producto"
                    : "No hay productos disponibles"}
                </span>
                <svg
                  className={`w-5 h-5 text-gray-400 transition-transform ${
                    showProductDropdown ? "rotate-180" : ""
                  }`}
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    d="M19 9l-7 7-7-7"
                  />
                </svg>
              </button>

              {showProductDropdown && productos.length > 0 && (
                <div className="absolute top-full left-0 right-0 bg-white border border-gray-200 rounded-lg shadow-lg z-10 max-h-60 overflow-y-auto">
                  {productos.map((product) => (
                    <button
                      key={product.id}
                      onClick={() => handleProductSelect(product)}
                      className="w-full text-left px-4 py-3 hover:bg-gray-100 border-b border-gray-100 last:border-b-0"
                    >
                      <div className="font-medium">{product.nombre}</div>
                      <div className="text-sm text-gray-600">
                        Cantidad: {product.cantidad} | Precio: $
                        {parseInt(product.precio).toLocaleString()}
                      </div>
                      <div className="text-xs text-gray-500">
                        Tallas: {product.tallas.join(", ")}
                      </div>
                    </button>
                  ))}
                </div>
              )}

              {showProductDropdown && productos.length === 0 && (
                <div className="absolute top-full left-0 right-0 bg-white border border-gray-200 rounded-lg shadow-lg z-10 p-4 text-center text-gray-500">
                  No hay productos disponibles. Crea productos primero.
                </div>
              )}
            </div>
          </div>

          {/* Dropdown Razón */}
          <div className="relative">
            <label className="block text-lg font-semibold text-black mb-2">
              Razón
            </label>
            <div className="relative">
              <button
                onClick={() => setShowReasonDropdown(!showReasonDropdown)}
                className="w-full bg-white rounded-full px-4 py-3 text-left flex items-center justify-between focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
              >
                <span
                  className={selectedReason ? "text-black" : "text-gray-400"}
                >
                  {selectedReason || "Seleccionar razón"}
                </span>
                <svg
                  className={`w-5 h-5 text-gray-400 transition-transform ${
                    showReasonDropdown ? "rotate-180" : ""
                  }`}
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    d="M19 9l-7 7-7-7"
                  />
                </svg>
              </button>

              {showReasonDropdown && (
                <div className="absolute top-full left-0 right-0 bg-white border border-gray-200 rounded-lg shadow-lg z-10">
                  {razonesDisponibles.map((reason) => (
                    <button
                      key={reason}
                      onClick={() => handleReasonSelect(reason)}
                      className="w-full text-left px-4 py-3 hover:bg-gray-100 border-b border-gray-100 last:border-b-0"
                    >
                      {reason}
                    </button>
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
        {/* Mensaje de advertencia o sección de tallas */}
        {showWarning ? (
          <div className="bg-gray-200 rounded-2xl p-12 text-center">
            <div className="w-12 h-12 bg-red-500 rounded-full flex items-center justify-center mx-auto mb-4">
              <span className="text-white font-bold text-xl">W</span>
            </div>
            <p className="text-lg font-semibold text-gray-800">
              {productos.length === 0
                ? "No hay productos disponibles.\nCrea productos primero."
                : "Selecciona un producto\npara continuar"}
            </p>
          </div>
        ) : (
          <div className="bg-gray-200 rounded-2xl p-6">
            <h3 className="text-xl font-bold text-black mb-6">Tallas</h3>

            {/* Información del producto seleccionado */}
            <div className="bg-white rounded-lg p-4 mb-6">
              <h4 className="font-semibold text-lg mb-2">
                Producto seleccionado:
              </h4>
              <p className="text-gray-700">
                <strong>Nombre:</strong> {selectedProduct?.nombre}
              </p>
              <p className="text-gray-700">
                <strong>Cantidad actual:</strong> {selectedProduct?.cantidad}
              </p>
              <p className="text-gray-700">
                <strong>Precio:</strong> $
                {parseInt(selectedProduct?.precio || 0).toLocaleString()}
              </p>
            </div>

            {/* Grid de tallas */}
            <div className="grid grid-cols-2 md:grid-cols-5 gap-4 mb-8">
              {selectedProduct?.tallas.map((talla) => (
                <div key={talla} className="flex items-center gap-2">
                  <span className="font-semibold text-lg min-w-[40px]">
                    {talla}:
                  </span>
                  <input
                    type="number"
                    min="0"
                    value={tallasCantidades[talla] || 0}
                    onChange={(e) => handleTallaChange(talla, e.target.value)}
                    className="w-16 h-8 px-2 border border-gray-300 rounded text-center focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
                  />
                </div>
              ))}
            </div>

            {/* Resumen de cantidad total */}
            <div className="bg-white rounded-lg p-4 mb-6">
              <p className="text-lg font-semibold">
                Total de unidades:{" "}
                {Object.values(tallasCantidades).reduce(
                  (sum, cant) => sum + cant,
                  0
                )}
              </p>
              {selectedReason && (
                <p className="text-sm text-gray-600 mt-1">
                  Tipo de movimiento:{" "}
                  {selectedReason.includes("VENTA") ||
                  selectedReason.includes("DAÑADO") ||
                  selectedReason.includes("REGALO")
                    ? "Salida de inventario"
                    : "Entrada de inventario"}
                </p>
              )}
            </div>

            {/* Botón Añadir Movimiento */}
            <div className="flex justify-center">
              <button
                onClick={handleSubmit}
                className="bg-[#FF7700] hover:bg-[#e06600] text-white font-bold py-4 px-8 rounded-full text-lg transition-colors cursor-pointer"
              >
                AÑADIR MOVIMIENTO
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default AnadirMovimiento;
