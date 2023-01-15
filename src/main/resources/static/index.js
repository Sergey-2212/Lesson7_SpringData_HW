angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.loadProducts = function () {
        console.log("loadProducts")
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                //делаю универсальную функцию, которая при наличии переданных значений min и max пробрасывает их в контроллер
                // при отсутствии передает null
                min: $scope.productListFilter ? $scope.productListFilter.min : null,
                max: $scope.productListFilter ? $scope.productListFilter.max : null,
                pageNumber: $scope.productListFilter ? $scope.productListFilter.pageNumber : null,
                pageSize: $scope.productListFilter ? $scope.productListFilter.pageSize : null,
                titleLike: $scope.productListFilter ? $scope.productListFilter.titleLike : null,
                sortProp: $scope.productListFilter ? $scope.productListFilter.sortProp : null,

            }
        }).then(function (response) {
            console.log(response);
            $scope.ProductsList = response.data.content;
            console.log(JSON.parse(response.data.pageable.pageSize));
        })
    };



    // $scope.loadFilteredProducts = function () {
    //     console.log("loadFilteredProducts")
    //     $http({
    //         url: contextPath + '/products/filter',
    //         method: 'POST',
    //         params: {
    //             min: $scope.productListFilter.min,
    //             max: $scope.productListFilter.max,
    //         }
    //     }).then(function (response) {
    //             console.log(response);
    //             $scope.ProductsList = response.data;
    //         })
    // };
/* Пример использования условий в JS
    $scope.currentPage = function () {
        if($scope.productListFilter.min === null && $scope.productListFilter.max === 0) {
            $scope.loadProducts();
        } else {
            $scope.loadFilteredProducts()
        }
    }

 */
    /* Чтобы функцию созданну в js можно было использовать в html, её нужно положить в scope*/
    $scope.deleteProduct = function (productId) {
       /* alert(studentId); /* выведем в алерте переданный studentId */
        $http({
            url: contextPath + '/products/',
            method: 'DELETE',
            params: {
                id: productId,
            }
        })
            .then(function (response) {
                alert('Deleted');
                $scope.loadProducts();  /* перегружаем страницу со списком продуктов */
            });
    }

    $scope.changePrice = function (studentId, delta) {
        /*Форма для создания шаблона get запроса с @RequestParam*/
        $http({
            url: contextPath + '/products/change_price/',
            method: 'PUT',
            params: {
                studentId: studentId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        })
    }

    $scope.createProductJSON = function () {
        $scope.newProductJSON.id = '1';
        console.log($scope.newProductJSON);

        $http.post(contextPath + '/products/', $scope.newProductJSON)
        // $http({
        //     url: contextPath + '/products/',
        //     method: 'POST',
        //     params: {
        //         id: $scope.newProductJSON.id,
        //         title: $scope.newProductJSON.title,
        //         price: $scope.newProductJSON.price,
        //     }
            .then(function(response) {
            alert("Продукт - " + response.data.title + " добавлен.")
            $scope.loadProducts();
        })
    }


    console.log("Перед загрузкой продукта")
    $scope.loadProducts();
    console.log("End of html.")
});