//package uz.elmurodov.services;
//
//import uz.elmurodov.criteria.BaseCriteria;
//import uz.elmurodov.dto.BaseDto;
//import uz.elmurodov.dto.BaseGenericDto;
//import uz.elmurodov.http.Data;
//import uz.elmurodov.http.ResponseEntity;
//
//import java.util.List;
//
///**
// * @author Elmurodov Javohir, Wed 11:12 AM. 12/15/2021
// */
//
///**
// * @param <D>  -> DTO
// * @param <CD> -> Create DTO
// * @param <UD> -> Update DTO
// * @param <ID> -> Primary Key(id)
// * @param <C>  -> Criteria
// */
//public interface IBaseCrudService<
//        D extends BaseGenericDto,
//        CD extends BaseDto,
//        UD extends BaseGenericDto,
//        C extends BaseCriteria,
//        ID extends Number> {
//
//    ResponseEntity<Data<String>> save(CD e);
//
//    ResponseEntity<Data<D>> get(ID id);
//
//    ResponseEntity<Data<List<D>>> getAll(C c);
//
//    ResponseEntity<Data<String>> update(UD e);
//
//    ResponseEntity<Data<String>> delete(ID id);
//}
