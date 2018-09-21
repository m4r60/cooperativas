package com.proyecto.cooperativa.models;

import lombok.*;

import java.io.Serializable;
//@toString() - Cuando pintas un objeto sale una referencia. Si pones toString()
//Se pinta el contenido del objeto.
//Si quieres determinar un tipo de igualdad - @EqualsAndHashCode
//Equal and HashCode
/*
*@Data is a convenient shortcut annotation that bundles
* the features of @ToString, @EqualsAndHashCode,
* @Getter / @Setter and @RequiredArgsConstructor together:
* In other words, @Data generates all the boilerplate that
* is normally associated with simple POJOs
* (Plain Old Java Objects) and beans: getters for all fields,
* setters for all non-final fields, and appropriate toString,
* equals and hashCode implementations that involve the fields
* of the class, and a constructor that initializes all final
* fields, as well as all non-final fields with no initializer
* that have been marked with @NonNull, in order to ensure the
* field is never null.
* */

/*
@RequiredArgsConstructor generates a
constructor with 1 parameter for each
field that requires special handling. All non-initialized
final fields get a parameter, as well as any fields that
are marked as @NonNull that aren't initialized where they are declared. For those fields marked with @NonNull, an explicit null check is also generated. The constructor will throw a NullPointerException if any of the parameters intended for the fields marked with @NonNull contain null. The order of the parameters match the order in which the fields appear in your class.
*/
@Data //Implementaciones: @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
//Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.
@AllArgsConstructor //Tengo un constructor con todos los elementos.
@NoArgsConstructor
public class Person implements Serializable {
    private int personId;
    private String cifNif;
    private String name;
    private String lastName;
    private String adress;
    private String phoneNumber;
    private String email;

}
