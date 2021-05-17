//
//  NSObject+RSPerson.h
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import <Foundation/Foundation.h>

@interface RSPerson: NSObject {
    NSString *_name;
}

- (void)sayHello;

- (instancetype)initWithName:(NSString*)name;

@end

