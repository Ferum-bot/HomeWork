//
//  main.m
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import <Foundation/Foundation.h>
#import "RSPerson.h"
#import "RSCarPort.h"
#import "RSPassenger+RS.h"

int main(int argc, const char * argv[]) {
    
    @autoreleasepool {
        RSCarPort* carPort = [RSCarPort new];
        
        RSPassenger* matvey = [[RSPassenger alloc] initWithName:@"Matvey"];
        RSCar* matveyCar = [[RSCar alloc] initWithPassenger:matvey];
        
        [carPort launchCar:matveyCar];
        
        [carPort enter:matvey];
        [carPort enter:matveyCar];
        
        [matvey sayHello];
    }
    
    return 0;
}
