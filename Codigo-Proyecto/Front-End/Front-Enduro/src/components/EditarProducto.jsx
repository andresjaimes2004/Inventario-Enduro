// import React, { useState, useEffect } from "react";
// import { useNavigate, useParams } from "react-router-dom";

// const EditarProducto = () => {
//   const { id } = useParams(); // Obtener el ID del producto desde la URL
//   const navigate = useNavigate();

//   const [productData, setProductData] = useState({
//     nombre: "",
//     tallas: [],
//     precio: "",
//   });
//   const [selectedImage, setSelectedImage] = useState(null);
//   const [imagePreview, setImagePreview] = useState(null);
//   const [loading, setLoading] = useState(true);

//   const tallasDisponibles = [35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45];

//   // Simular carga de datos del producto existente
//   useEffect(() => {
//     const cargarProducto = async () => {
//       try {
//         // Aquí iría la llamada a la API para obtener el producto por ID
//         // Por ahora simulamos datos de ejemplo
//         const productoExistente = {
//           id: id,
//           nombre: "Nike Air Max 270 - Producto Ejemplo",
//           tallas: [36, 37, 38, 39, 40],
//           precio: "150000",
//           imagen: null, // URL de la imagen existente si la hay
//         };

//         setProductData({
//           nombre: productoExistente.nombre,
//           tallas: productoExistente.tallas,
//           precio: productoExistente.precio,
//         });

//         if (productoExistente.imagen) {
//           setImagePreview(productoExistente.imagen);
//         }

//         setLoading(false);
//       } catch (error) {
//         console.error("Error al cargar el producto:", error);
//         setLoading(false);
//       }
//     };

//     if (id) {
//       cargarProducto();
//     }
//   }, [id]);

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setProductData((prev) => ({
//       ...prev,
//       [name]: value,
//     }));
//   };

//   const handleTallaChange = (talla) => {
//     setProductData((prev) => ({
//       ...prev,
//       tallas: prev.tallas.includes(talla)
//         ? prev.tallas.filter((t) => t !== talla)
//         : [...prev.tallas, talla].sort((a, b) => a - b),
//     }));
//   };

//   const handleImageChange = (e) => {
//     const file = e.target.files[0];
//     if (file) {
//       setSelectedImage(file);
//       const reader = new FileReader();
//       reader.onload = (e) => {
//         setImagePreview(e.target.result);
//       };
//       reader.readAsDataURL(file);
//     }
//   };

//   const handleDragOver = (e) => {
//     e.preventDefault();
//   };

//   const handleDrop = (e) => {
//     e.preventDefault();
//     const file = e.dataTransfer.files[0];
//     if (file && file.type.startsWith("image/")) {
//       setSelectedImage(file);
//       const reader = new FileReader();
//       reader.onload = (e) => {
//         setImagePreview(e.target.result);
//       };
//       reader.readAsDataURL(file);
//     }
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     console.log("Producto a editar:", { id, ...productData });
//     console.log("Nueva imagen:", selectedImage);
//     // Aquí iría la lógica para actualizar el producto existente
//     alert("Producto editado exitosamente (demo)");
//     navigate("/Productos");
//   };

//   const goBack = () => {
//     navigate("/Productos");
//   };

//   if (loading) {
//     return (
//       <div className="w-full min-h-screen bg-gray-50 flex items-center justify-center">
//         <div className="text-center">
//           <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#FF7700] mx-auto mb-4"></div>
//           <p className="text-gray-600">Cargando producto...</p>
//         </div>
//       </div>
//     );
//   }

//   return (
//     <div className="w-full min-h-screen bg-gray-50 flex flex-col">
//       <div className="flex-1 flex flex-col max-w-7xl mx-auto w-full py-4">
//         {/* Header */}
//         <div className="flex-shrink-0 px-4 pb-2">
//           <button
//             onClick={goBack}
//             className="mb-2 text-orange-400 hover:text-orange-500 cursor-pointer font-medium flex items-center gap-2"
//           >
//             ← Volver a Productos
//           </button>
//           <h1 className="text-2xl font-bold text-gray-900">Editar Producto</h1>
//         </div>

//         {/* Contenedor Principal - Responsive */}
//         <div className="flex flex-col lg:flex-row gap-4 px-4 pb-4">
//           {/* Sección Formulario - Responsive */}
//           <div className="w-full lg:w-2/5 bg-[#AFCBD3] rounded-xl shadow-lg flex flex-col min-h-[500px]">
//             <div className="flex-shrink-0 p-4">
//               <h2 className="text-lg font-semibold text-gray-800">
//                 Información del Producto
//               </h2>
//             </div>

//             <div className="flex-1 flex flex-col p-4 pt-5">
//               {/* Contenido del formulario */}
//               <div className="flex-1 space-y-8">
//                 {/* Nombre del Producto */}
//                 <div>
//                   <label className="block text-sm font-medium text-gray-700 mb-1">
//                     Nombre del Producto
//                   </label>
//                   <input
//                     type="text"
//                     name="nombre"
//                     value={productData.nombre}
//                     onChange={handleInputChange}
//                     className="w-full px-3 py-2 bg-white border-0 rounded-2xl"
//                     placeholder="Ej: Nike Air Max 270"
//                     required
//                   />
//                 </div>

//                 {/* Tallas Disponibles */}
//                 <div>
//                   <label className="block text-sm font-medium text-gray-700 mb-2">
//                     Tallas Disponibles
//                   </label>
//                   <div className="grid grid-cols-3 sm:grid-cols-4 gap-2">
//                     {tallasDisponibles.map((talla) => (
//                       <button
//                         key={talla}
//                         type="button"
//                         onClick={() => handleTallaChange(talla)}
//                         className={`py-1.5 px-2 cursor-pointer rounded-lg border-2 font-medium transition-all text-sm ${
//                           productData.tallas.includes(talla)
//                             ? "bg-[#FF7700] text-white border-orange-400 "
//                             : "bg-white text-gray-700 border-gray-300 hover:bg-orange-500 hover:text-white hover:border-orange-400"
//                         }`}
//                       >
//                         {talla}
//                       </button>
//                     ))}
//                   </div>
//                   {productData.tallas.length > 0 && (
//                     <p className="mt-1 text-sm text-gray-600">
//                       Seleccionadas: {productData.tallas.join(", ")}
//                     </p>
//                   )}
//                 </div>
//               </div>

//               {/* Botones de Acción */}
//               <div className="flex-shrink-0 flex flex-col sm:flex-row gap-2 mt-4 pt-3 border-t border-gray-100">
//                 <button
//                   type="button"
//                   onClick={goBack}
//                   className="flex-1 px-2 py-2 cursor-pointer bg-white border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 font-medium transition-colors text-sm"
//                 >
//                   Cancelar
//                 </button>
//                 <button
//                   type="button"
//                   onClick={handleSubmit}
//                   className="flex-1 px-2 py-2 cursor-pointer bg-[#FF7700] text-white rounded-lg hover:bg-[#ff7700ce] font-medium transition-colors text-sm"
//                 >
//                   Editar Producto
//                 </button>
//               </div>
//             </div>
//           </div>

//           {/* Sección Imagen - Responsive */}
//           <div className="w-full lg:w-3/5 bg-[#AFCBD3] rounded-xl shadow-lg">
//             <div className="p-3 border-b border-gray-200">
//               <h2 className="text-xl font-semibold text-gray-800">
//                 Imagen del Producto
//               </h2>
//             </div>

//             <div className="p-3">
//               {/* Área de Upload */}
//               <div
//                 className="border-2 border-dashed border-gray-400 rounded-xl flex flex-col items-center justify-center cursor-pointer hover:border-[#FF7700] hover:bg-orange-50 transition-colors h-[400px]"
//                 onDragOver={handleDragOver}
//                 onDrop={handleDrop}
//                 onClick={() => document.getElementById("imageInput").click()}
//               >
//                 {imagePreview ? (
//                   <div className="w-full h-full flex flex-col items-center justify-center">
//                     <div className="flex-1 flex items-center justify-center w-full h-full overflow-hidden">
//                       <img
//                         src={imagePreview}
//                         alt="Preview"
//                         className="max-w-full max-h-full object-contain rounded-lg shadow-lg"
//                         style={{ maxHeight: "380px", maxWidth: "100%" }}
//                       />
//                     </div>
//                     <p className="mt-3 text-sm text-gray-700 flex-shrink-0">
//                       Haz clic para cambiar la imagen
//                     </p>
//                   </div>
//                 ) : (
//                   <div className="text-center p-8">
//                     <div className="w-20 h-20 mx-auto mb-6 bg-gray-200 rounded-full flex items-center justify-center">
//                       <svg
//                         className="w-10 h-10 text-gray-500"
//                         fill="none"
//                         stroke="currentColor"
//                         viewBox="0 0 24 24"
//                       >
//                         <path
//                           strokeLinecap="round"
//                           strokeLinejoin="round"
//                           strokeWidth={2}
//                           d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"
//                         />
//                       </svg>
//                     </div>
//                     <h3 className="text-xl font-medium text-gray-800 mb-3">
//                       Sube una imagen del producto
//                     </h3>
//                     <p className="text-gray-700 mb-4">
//                       Arrastra y suelta una imagen aquí, o haz clic para
//                       seleccionar
//                     </p>
//                     <p className="text-sm text-gray-600">
//                       PNG, JPG, GIF hasta 10MB
//                     </p>
//                   </div>
//                 )}
//               </div>

//               {/* Input oculto */}
//               <input
//                 id="imageInput"
//                 type="file"
//                 accept="image/*"
//                 onChange={handleImageChange}
//                 className="hidden"
//               />

//               {/* Información adicional */}
//               {selectedImage && (
//                 <div className="mt-4 p-1 bg-gray-100 rounded-lg border border-gray-300">
//                   <p className="text-sm text-gray-700 mb-1">
//                     <strong>Archivo:</strong> {selectedImage.name}
//                   </p>
//                   <p className="text-sm text-gray-700">
//                     <strong>Tamaño:</strong>{" "}
//                     {(selectedImage.size / 1024 / 1024).toFixed(2)} MB
//                   </p>
//                 </div>
//               )}
//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default EditarProducto;

import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

const EditarProducto = () => {
  const { id } = useParams(); // Obtener el ID del producto desde la URL
  const navigate = useNavigate();

  const [productData, setProductData] = useState({
    nombre: "",
    tallas: [],
    precio: "",
    cantidad: 20,
  });
  const [selectedImage, setSelectedImage] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const tallasDisponibles = [35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45];

  // Cargar datos del producto existente
  useEffect(() => {
    const cargarProducto = async () => {
      try {
        // Obtener productos desde localStorage
        const savedProducts = localStorage.getItem("productos");
        if (savedProducts) {
          const productos = JSON.parse(savedProducts);
          const producto = productos.find((p) => p.id === parseInt(id));

          if (producto) {
            setProductData({
              nombre: producto.nombre,
              tallas: producto.tallas,
              precio: producto.precio || "",
              cantidad: producto.cantidad || 20,
            });

            if (producto.imagen) {
              setImagePreview(producto.imagen);
            }
          } else {
            setError("Producto no encontrado");
          }
        } else {
          setError("No se encontraron productos");
        }

        setLoading(false);
      } catch (error) {
        console.error("Error al cargar el producto:", error);
        setError("Error al cargar el producto");
        setLoading(false);
      }
    };

    if (id) {
      cargarProducto();
    }
  }, [id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProductData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleTallaChange = (talla) => {
    setProductData((prev) => ({
      ...prev,
      tallas: prev.tallas.includes(talla)
        ? prev.tallas.filter((t) => t !== talla)
        : [...prev.tallas, talla].sort((a, b) => a - b),
    }));
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedImage(file);
      const reader = new FileReader();
      reader.onload = (e) => {
        setImagePreview(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const file = e.dataTransfer.files[0];
    if (file && file.type.startsWith("image/")) {
      setSelectedImage(file);
      const reader = new FileReader();
      reader.onload = (e) => {
        setImagePreview(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validaciones básicas
    if (!productData.nombre.trim()) {
      alert("Por favor ingresa el nombre del producto");
      return;
    }

    if (productData.tallas.length === 0) {
      alert("Por favor selecciona al menos una talla");
      return;
    }

    try {
      // Obtener productos actuales
      const savedProducts = localStorage.getItem("productos");
      let productos = [];

      if (savedProducts) {
        productos = JSON.parse(savedProducts);
      }

      // Encontrar y actualizar el producto
      const productoIndex = productos.findIndex((p) => p.id === parseInt(id));

      if (productoIndex !== -1) {
        // Actualizar el producto existente
        productos[productoIndex] = {
          ...productos[productoIndex],
          nombre: productData.nombre,
          tallas: productData.tallas,
          precio: productData.precio,
          cantidad: productData.cantidad,
          imagen: imagePreview, // Usar la imagen previa (nueva o existente)
        };

        // Guardar en localStorage
        localStorage.setItem("productos", JSON.stringify(productos));

        // Disparar evento personalizado para notificar cambios
        window.dispatchEvent(
          new CustomEvent("productUpdated", {
            detail: productos,
          })
        );

        console.log("Producto actualizado:", productos[productoIndex]);
        alert("Producto editado exitosamente");
        navigate("/Productos");
      } else {
        alert("Error: Producto no encontrado");
      }
    } catch (error) {
      console.error("Error al guardar el producto:", error);
      alert("Error al guardar los cambios");
    }
  };

  const goBack = () => {
    navigate("/Productos");
  };

  if (loading) {
    return (
      <div className="w-full min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#FF7700] mx-auto mb-4"></div>
          <p className="text-gray-600">Cargando producto...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="w-full min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="text-red-500 text-6xl mb-4">⚠️</div>
          <h2 className="text-2xl font-bold text-gray-800 mb-2">Error</h2>
          <p className="text-gray-600 mb-4">{error}</p>
          <button
            onClick={goBack}
            className="bg-[#FF7700] hover:bg-[#e06600] text-white px-6 py-2 rounded-lg font-medium transition-colors cursor-pointer"
          >
            Volver a Productos
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="w-full min-h-screen bg-gray-50 flex flex-col">
      <div className="flex-1 flex flex-col max-w-7xl mx-auto w-full py-4">
        {/* Header */}
        <div className="flex-shrink-0 px-4 pb-2">
          <button
            onClick={goBack}
            className="mb-2 text-orange-400 hover:text-orange-500 cursor-pointer font-medium flex items-center gap-2"
          >
            ← Volver a Productos
          </button>
          <h1 className="text-2xl font-bold text-gray-900">Editar Producto</h1>
        </div>

        {/* Contenedor Principal - Responsive */}
        <form
          onSubmit={handleSubmit}
          className="flex flex-col lg:flex-row gap-4 px-4 pb-4"
        >
          {/* Sección Formulario - Responsive */}
          <div className="w-full lg:w-2/5 bg-[#AFCBD3] rounded-xl shadow-lg flex flex-col min-h-[500px]">
            <div className="flex-shrink-0 p-4">
              <h2 className="text-lg font-semibold text-gray-800">
                Información del Producto
              </h2>
            </div>

            <div className="flex-1 flex flex-col p-4 pt-5">
              {/* Contenido del formulario */}
              <div className="flex-1 space-y-8">
                {/* Nombre del Producto */}
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">
                    Nombre del Producto *
                  </label>
                  <input
                    type="text"
                    name="nombre"
                    value={productData.nombre}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 bg-white border-0 rounded-2xl focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
                    placeholder="Ej: Nike Air Max 270"
                    required
                  />
                </div>

                {/* Precio del Producto */}
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">
                    Precio (COP)
                  </label>
                  <input
                    type="number"
                    name="precio"
                    value={productData.precio}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 bg-white border-0 rounded-2xl focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
                    placeholder="Ej: 150000"
                    min="0"
                  />
                </div>

                {/* Cantidad */}
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">
                    Cantidad en Stock
                  </label>
                  <input
                    type="number"
                    name="cantidad"
                    value={productData.cantidad}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 bg-white border-0 rounded-2xl focus:outline-none focus:ring-2 focus:ring-[#FF7700]"
                    placeholder="Ej: 20"
                    min="0"
                  />
                </div>

                {/* Tallas Disponibles */}
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Tallas Disponibles *
                  </label>
                  <div className="grid grid-cols-3 sm:grid-cols-4 gap-2">
                    {tallasDisponibles.map((talla) => (
                      <button
                        key={talla}
                        type="button"
                        onClick={() => handleTallaChange(talla)}
                        className={`py-1.5 px-2 cursor-pointer rounded-lg border-2 font-medium transition-all text-sm ${
                          productData.tallas.includes(talla)
                            ? "bg-[#FF7700] text-white border-orange-400 "
                            : "bg-white text-gray-700 border-gray-300 hover:bg-orange-500 hover:text-white hover:border-orange-400"
                        }`}
                      >
                        {talla}
                      </button>
                    ))}
                  </div>
                  {productData.tallas.length > 0 && (
                    <p className="mt-1 text-sm text-gray-600">
                      Seleccionadas: {productData.tallas.join(", ")}
                    </p>
                  )}
                </div>
              </div>

              {/* Botones de Acción */}
              <div className="flex-shrink-0 flex flex-col sm:flex-row gap-2 mt-4 pt-3 border-t border-gray-100">
                <button
                  type="button"
                  onClick={goBack}
                  className="flex-1 px-2 py-2 cursor-pointer bg-white border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 font-medium transition-colors text-sm"
                >
                  Cancelar
                </button>
                <button
                  type="submit"
                  className="flex-1 px-2 py-2 cursor-pointer bg-[#FF7700] text-white rounded-lg hover:bg-[#ff7700ce] font-medium transition-colors text-sm"
                >
                  Guardar Cambios
                </button>
              </div>
            </div>
          </div>

          {/* Sección Imagen - Responsive */}
          <div className="w-full lg:w-3/5 bg-[#AFCBD3] rounded-xl shadow-lg">
            <div className="p-3 border-b border-gray-200">
              <h2 className="text-xl font-semibold text-gray-800">
                Imagen del Producto
              </h2>
            </div>

            <div className="p-3">
              {/* Área de Upload */}
              <div
                className="border-2 border-dashed border-gray-400 rounded-xl flex flex-col items-center justify-center cursor-pointer hover:border-[#FF7700] hover:bg-orange-50 transition-colors h-[400px]"
                onDragOver={handleDragOver}
                onDrop={handleDrop}
                onClick={() => document.getElementById("imageInput").click()}
              >
                {imagePreview ? (
                  <div className="w-full h-full flex flex-col items-center justify-center">
                    <div className="flex-1 flex items-center justify-center w-full h-full overflow-hidden">
                      <img
                        src={imagePreview}
                        alt="Preview"
                        className="max-w-full max-h-full object-contain rounded-lg shadow-lg"
                        style={{ maxHeight: "380px", maxWidth: "100%" }}
                      />
                    </div>
                    <p className="mt-3 text-sm text-gray-700 flex-shrink-0">
                      Haz clic para cambiar la imagen
                    </p>
                  </div>
                ) : (
                  <div className="text-center p-8">
                    <div className="w-20 h-20 mx-auto mb-6 bg-gray-200 rounded-full flex items-center justify-center">
                      <svg
                        className="w-10 h-10 text-gray-500"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth={2}
                          d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"
                        />
                      </svg>
                    </div>
                    <h3 className="text-xl font-medium text-gray-800 mb-3">
                      Sube una imagen del producto
                    </h3>
                    <p className="text-gray-700 mb-4">
                      Arrastra y suelta una imagen aquí, o haz clic para
                      seleccionar
                    </p>
                    <p className="text-sm text-gray-600">
                      PNG, JPG, GIF hasta 10MB
                    </p>
                  </div>
                )}
              </div>

              {/* Input oculto */}
              <input
                id="imageInput"
                type="file"
                accept="image/*"
                onChange={handleImageChange}
                className="hidden"
              />

              {/* Información adicional */}
              {selectedImage && (
                <div className="mt-4 p-1 bg-gray-100 rounded-lg border border-gray-300">
                  <p className="text-sm text-gray-700 mb-1">
                    <strong>Archivo:</strong> {selectedImage.name}
                  </p>
                  <p className="text-sm text-gray-700">
                    <strong>Tamaño:</strong>{" "}
                    {(selectedImage.size / 1024 / 1024).toFixed(2)} MB
                  </p>
                </div>
              )}
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditarProducto;
