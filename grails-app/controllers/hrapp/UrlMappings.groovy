package hrapp

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")
        get "/commonCodes"(controller: "commonCode", action: "commonCodes")
        post "/commonCodes"(controller:"commonCode", action: "addNewCodes")
        "/product/$id"(controller:"product", action: "update", method: "PUT")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
