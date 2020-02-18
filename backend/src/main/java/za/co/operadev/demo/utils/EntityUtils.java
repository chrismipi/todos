package za.co.operadev.demo.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityUtils {
    private final ModelMapper modelMapper;

    public EntityUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> Object convertToDto(Object objEntity, Class<T> clazz) {
        Object objDto = modelMapper.map(objEntity, clazz);
        return clazz.cast(objDto);
    }

    public <T> Object convertToEntity(Object objDto, Class<T> clazz){
        return modelMapper.map(objDto, clazz);
    }
}
