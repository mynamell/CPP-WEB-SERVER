#include "user.hpp"

nlohmann::json User::toJson()const{
    return{
        {"username",username},
        {"password",password},
        {"is_online",is_online}
    };

}

User user::fromJson(const nlohmann::json& j){
    User u;
    u.username=j.at("username").get<std::string>();
    u.password=j.at("password").get<std::string>();
    u.is_online=j.at("is_online").get<bool>();
    return u;
}