import UserRespository from "../repository/UserRespository";
import UserException from "../exception/UserException";

import * as httpStatus from "../../../config/constants/httpStatus";

class UserService {
  async findByEmail(req) {
    try {
      const { email } = req.params;
      this.validateRequestData(email);
      let user = UserRespository.findByEmail(email);

      if (!user) {
      }
      return {
        status: httpStatus.SUCCESS,
        user: {
          id: user.id,
          nome: user.name,
          email: user.email,
        },
      };
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.status,
      };
    }
  }

  validateRequestData(email) {
    if (!email) {
      throw new Error("User email was not informed");
    }
  }
}

export default new UserService();
